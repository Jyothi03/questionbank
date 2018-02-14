package org.questionBank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.questionBank.dao.DepartmentDataUtil;
import org.questionBank.dao.PersonDataUtil;
import org.questionBank.data.Department;
import org.questionBank.data.Person;
import org.questionBank.exception.InvalidDepartmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentDataUtil departmentDAO;
	@Autowired
	PersonDataUtil personDAO;

	// List
	@RequestMapping(value="/DepartmentsView",method=RequestMethod.GET)
	public ModelAndView listDepartments(HttpServletRequest request){
		ModelAndView mve =  null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			List<Department> departments = departmentDAO.getDepartments();
			mve = new ModelAndView("views/departments/ListDepartments");
			mve.addObject("departments",departments);
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			return mve;
		}else{
			mve = new ModelAndView("index");
			mve.addObject("message", "Invalid User ID for Session");
			return mve;
		}
	}

	// Create
	@RequestMapping(value="/AddDepartment",method=RequestMethod.GET)
	public ModelAndView addDepartmentView(HttpServletRequest request, @RequestParam(required=false) String name, @RequestParam(required=false) String abbreviation){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Department d = null;
			boolean newDepartment = true;
			if(name != null || abbreviation != null){
				d = departmentDAO.populateDepartment(name, abbreviation);
				newDepartment = false;
			}else{
				d = new Department();
			}
			mve = new ModelAndView("views/departments/AddDepartment");
			if(d != null){
				mve.addObject("dept", d);
				if(!newDepartment)
					mve.addObject("errors", departmentDAO.departmentErrors(d));
			}
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}

	@RequestMapping(value="/AddDepartment",method=RequestMethod.POST)
	public ModelAndView createCourse(HttpServletRequest request, @ModelAttribute("dept") Department dept){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			try {
				Department newDepartment = departmentDAO.createDepartment(dept);
				mve=new ModelAndView("redirect:ShowDepartment?id="+newDepartment.getId());
				mve.addObject("department", newDepartment);
				Integer userId = (Integer) uid;
				Person curUser = personDAO.findPerson(userId);
				mve.addObject("isAdmin", curUser.isAdmin());
			} catch (InvalidDepartmentException e) {
				mve= new ModelAndView("redirect:AddDepartment");
				mve.addObject("name", dept.getName());
				mve.addObject("abbreviation", dept.getAbbreviation());
				Integer userId = (Integer) uid;
				Person curUser = personDAO.findPerson(userId);
				mve.addObject("isAdmin", curUser.isAdmin());
			}
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	// Show
	@RequestMapping(value="/ShowDepartment",method=RequestMethod.GET)
	public ModelAndView showDepartment(HttpServletRequest request, @RequestParam("id") int id){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			mve = new ModelAndView("views/departments/ShowDepartment");
			Department d = departmentDAO.findDepartment(id);
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			mve.addObject("department",d);
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	// Edit
	@RequestMapping(value="/EditDepartment",method=RequestMethod.GET)
	public ModelAndView editDepartment(HttpServletRequest request, @RequestParam("id") int id){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			mve = new ModelAndView("views/departments/EditDepartment");
			Department d = departmentDAO.findDepartment(id);
			try{
				departmentDAO.validateDepartment(d);
			}catch(InvalidDepartmentException ex){
				mve.addObject("errors", departmentDAO.departmentErrors(d));
			}
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			mve.addObject("dept",d);
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}

	@RequestMapping(value="/UpdateDepartment",method=RequestMethod.POST)
	public ModelAndView updateDepartment(HttpServletRequest request, @ModelAttribute("dept") Department dept){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			try {
				departmentDAO.updateDepartment(dept);
				mve=new ModelAndView("redirect:ShowDepartment?id="+dept.getId());
				mve.addObject("dept", dept);
				Integer userId = (Integer) uid;
				Person curUser = personDAO.findPerson(userId);
				mve.addObject("isAdmin", curUser.isAdmin());
			} catch (InvalidDepartmentException e) {
				mve=new ModelAndView("redirect:EditDepartment?id="+dept.getId());
			}
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}

	// Helper methods
	private ModelAndView rejectInvalidUser(Integer uid){
		ModelAndView mve = new ModelAndView();
		mve = new ModelAndView("redirect:teacherlogin.jsp");
		if(uid == null)
			mve.addObject("message", "Invalid User ID for Session");
		else
			mve.addObject("message", "Invalid User ID ["+uid+"] for Session");
		return mve;
	}
}
