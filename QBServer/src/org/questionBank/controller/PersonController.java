package org.questionBank.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.questionBank.dao.PersonDataUtil;
import org.questionBank.data.Person;
import org.questionBank.exception.InvalidCredentialException;
import org.questionBank.exception.InvalidUserException;
import org.questionBank.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PersonController {
	
	@Autowired
	PersonDataUtil personDAO;
	
	//List
	@RequestMapping(value="/UsersView",method=RequestMethod.GET)
	public ModelAndView listUsers(HttpServletRequest request){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Integer userId = (Integer) uid;
			Person user = personDAO.findPerson(userId);
			if(user == null){
				return rejectInvalidUser(userId);
			} else if(user.isAdmin()){
				List<Person> users = personDAO.getAllUsers();
				mve = new ModelAndView("views/users/ListUsers");
				mve.addObject("isAdmin", user.isAdmin());
				mve.addObject("users", users);
				return mve;
			}else{
				Map<String,Object> props = new HashMap<String,Object>();
				props.put("isAdmin", user.isAdmin());
				return rejectPrivileges(props);
			}
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	//Show
	@RequestMapping(value="/ShowUser",method=RequestMethod.GET)
	public ModelAndView showUser(HttpServletRequest request, @RequestParam(required=true) Integer id){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			if(curUser == null){
				return rejectInvalidUser(userId);
			}else if(curUser.isAdmin() || userId.intValue() == id.intValue()){
				Person user = personDAO.findPerson(id);
				mve = new ModelAndView("views/users/ShowUser");
				mve.addObject("isAdmin", curUser.isAdmin());
				mve.addObject("user", user);
				return mve;
			}else{
				Map<String,Object> props = new HashMap<String,Object>();
				props.put("isAdmin", curUser.isAdmin());
				props.put("message", "You do not have proper privileges to view this User");
				return rejectPrivileges(props);
			}
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	@RequestMapping(value="/AddUser",method=RequestMethod.GET)
	public ModelAndView addNewUser(HttpServletRequest request, @RequestParam(required=false) String userName, @RequestParam(required=false) String firstName, 
			@RequestParam(required=false) String lastName, @RequestParam(required=false) String errors){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			if(curUser == null){
				return rejectInvalidUser(userId);
			}else if(curUser.isAdmin()){
				Person p = new Person();
				boolean newPerson = true;
				if(userName != null || firstName != null || lastName != null){
					p.setUserName(userName);
					p.setFirstName(firstName);
					p.setLastName(lastName);
					newPerson = false;
				}
				mve = new ModelAndView("views/users/AddUser");
				mve.addObject("user", p);
				mve.addObject("isAdmin", curUser.isAdmin());
				if(!newPerson)
					mve.addObject("errors", errors);
				return mve;
			}else{
				Map<String,Object> props = new HashMap<String,Object>();
				props.put("isAdmin", curUser.isAdmin());
				props.put("message", "You do not have proper privileges to view this User");
				return rejectPrivileges(props);
			}
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	@RequestMapping(value="/AddUser", method=RequestMethod.POST)
	public ModelAndView createUser(HttpServletRequest request, @RequestParam("userName") String userName,@RequestParam String password,@RequestParam("firstName") String firstName,@RequestParam String rpassword,
			@RequestParam("lastName") String lastName){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			if(curUser == null){
				return rejectInvalidUser(userId);
			}else if(curUser.isAdmin()){
				try{
					Person p = personDAO.createUser(userName, password, firstName, rpassword, lastName, false);
					mve =new ModelAndView("redirect:ShowUser");
					mve.addObject("id",p.getId());
					mve.addObject("isAdmin", curUser.isAdmin());
					return mve;
				} catch (InvalidCredentialException | UserAlreadyExistException e) {
					mve=new ModelAndView("redirect:AddUser");
					mve.addObject("userName", userName);
					mve.addObject("firstName", firstName);
					mve.addObject("lastName", lastName);
					mve.addObject("errors", e.getMessage());
					mve.addObject("isAdmin", curUser.isAdmin());
					return mve;
				}
			}else{
				Map<String,Object> props = new HashMap<String,Object>();
				props.put("isAdmin", curUser.isAdmin());
				props.put("message", "You do not have proper privileges to view this User");
				return rejectPrivileges(props);
			}
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	// Edit
	@RequestMapping(value="/EditUser",method=RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request, @RequestParam("id") Integer id, @RequestParam(required=false) String errors){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			if(curUser == null){
				return rejectInvalidUser(userId);
			}else if(curUser.isAdmin() || userId.intValue() == id.intValue()){
				Person user = personDAO.findPerson(id);
				mve = new ModelAndView("views/users/EditUser");
				try{
					personDAO.validateUser(user);
				}catch(InvalidUserException ex){
					mve.addObject("errors", personDAO.userErrors(user));
				}
				if(errors != null && !errors.trim().isEmpty())
					mve.addObject("errors", errors);
				mve.addObject("isAdmin", curUser.isAdmin());
				mve.addObject("user", user);
				return mve;
			}else{
				Map<String,Object> props = new HashMap<String,Object>();
				props.put("isAdmin", curUser.isAdmin());
				props.put("message", "You do not have proper privileges to edit this User");
				return rejectPrivileges(props);
			}
		}else{
			return rejectInvalidUser(null);
		}
	}

	@RequestMapping(value="/UpdateUser",method=RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, @ModelAttribute("user") Person user){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			if(curUser == null){
				return rejectInvalidUser(userId);
			}else if(curUser.isAdmin() || userId.intValue() == user.getId()){
				Integer id = user.getId();
				try {
					personDAO.updateUser(user);
					Person newUser = personDAO.findPerson(id);
					mve=new ModelAndView("redirect:ShowUser?id="+id);
					mve.addObject("user", newUser);
				} catch (InvalidUserException e) {
					mve=new ModelAndView("redirect:EditUser?id="+id);
					mve.addObject("errors", personDAO.userErrors(user));
				}
				return mve;
			}else{
				Map<String,Object> props = new HashMap<String,Object>();
				props.put("isAdmin", curUser.isAdmin());
				props.put("message", "You do not have proper privileges to edit this User");
				return rejectPrivileges(props);
			}
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	@RequestMapping(value="/ResetUserPsw",method=RequestMethod.GET)
	public ModelAndView resetUserPswView(HttpServletRequest request, @RequestParam("id") Integer id, @RequestParam(required=false) String errors){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			if(curUser == null){
				return rejectInvalidUser(userId);
			}else if(curUser.isAdmin() || userId.intValue() == id.intValue()){
				Person user = personDAO.findPerson(id);
				mve = new ModelAndView("views/users/ResetUserPsw");
				try{
					personDAO.validateUser(user);
				}catch(InvalidUserException ex){
					mve.addObject("errors", personDAO.userErrors(user));
				}
				if(errors != null && !errors.trim().isEmpty())
					mve.addObject("errors", errors);
				mve.addObject("isAdmin", curUser.isAdmin());
				mve.addObject("user", user);
				return mve;
			}else{
				Map<String,Object> props = new HashMap<String,Object>();
				props.put("isAdmin", curUser.isAdmin());
				props.put("message", "You do not have proper privileges to edit this User");
				return rejectPrivileges(props);
			}
		}else{
			return rejectInvalidUser(null);
		}
	}

	@RequestMapping(value="/ResetUserPsw",method=RequestMethod.POST)
	public ModelAndView resetUserPsw(HttpServletRequest request, @RequestParam Integer id,@RequestParam String password, @RequestParam String rpassword){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			if(curUser == null){
				return rejectInvalidUser(userId);
			}else if(curUser.isAdmin() || userId.intValue() == id){
				try {
					Person updatedUser = personDAO.resetUserPassword(id, password, rpassword);
					mve=new ModelAndView("redirect:ShowUser?id="+id);
					mve.addObject("user", updatedUser);
				} catch (InvalidCredentialException e) {
					mve=new ModelAndView("redirect:ResetUserPsw?id="+id);
					mve.addObject("errors", "Passwords did not match");
				} catch(InvalidUserException ex){
					mve=new ModelAndView("redirect:ResetUserPsw?id="+id);
					mve.addObject("errors", ex.getMessage());
				}
				return mve;
			}else{
				Map<String,Object> props = new HashMap<String,Object>();
				props.put("isAdmin", curUser.isAdmin());
				props.put("message", "You do not have proper privileges to edit this User");
				return rejectPrivileges(props);
			}
		} else {
			return rejectInvalidUser(null);	
		}
	}
	
	//Create
	@RequestMapping(value="TeacherSignup",method=RequestMethod.POST)
	public ModelAndView createPerson(HttpServletRequest request,@RequestParam("username") String userName,@RequestParam String password,@RequestParam("firstname") String firstName,@RequestParam String rpassword,@RequestParam("lastname") String lastName){
		ModelAndView modelAndView =  null;
		try {
			Person p = personDAO.createPerson(userName, password, firstName, rpassword, lastName);
			HttpSession session = request.getSession();
			session.setAttribute("name", p.getUserName());
			session.setAttribute("userId", p.getId());
			session.setAttribute("firstName", p.getFirstName());
			session.setAttribute("lastName", p.getLastName());
			modelAndView=new ModelAndView("redirect:teacherdashboard.jsp");
		} catch (InvalidCredentialException | UserAlreadyExistException e) {
			modelAndView=new ModelAndView("redirect:teachersignup.jsp");
			modelAndView.addObject("message", e.getMessage());
		}
		return modelAndView;
	}

	//login
	@RequestMapping(value="/teacherlogin",method=RequestMethod.POST)
	public ModelAndView teacherLogin(HttpServletRequest request,@RequestParam String username,@RequestParam String password){
		ModelAndView modelAndView = null; 
		try {
			Person p = personDAO.teacherLogin(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("name", p.getUserName());
			session.setAttribute("userId", p.getId());
			session.setAttribute("firstName", p.getFirstName());
			session.setAttribute("lastName", p.getLastName());
			if(p.isAdmin()){
				modelAndView=new ModelAndView("redirect:admindashboard.jsp");
			}else{
				modelAndView=new ModelAndView("redirect:teacherdashboard.jsp");
			}
		} catch (InvalidCredentialException e) {
			modelAndView=new ModelAndView("redirect:teacherlogin.jsp");
			modelAndView.addObject("message", e.getMessage());
		}
		return modelAndView;
	}
	
	@RequestMapping("teachersignout")
	public ModelAndView teachersignout(HttpServletRequest request){
		ModelAndView modelAndView =null; 
		try {
			request.getSession().invalidate();
			modelAndView=new ModelAndView("redirect:teacherlogin.jsp");
		} catch (Exception e) {
			modelAndView=new ModelAndView("redirect:teacherlogin.jsp");
			modelAndView.addObject("message", e.getMessage());
		}
		return modelAndView;
	}
	
	@RequestMapping(value="AdminLogin",method=RequestMethod.POST)
	public ModelAndView adminLogin(HttpServletRequest request,@RequestParam String username,@RequestParam String password){
		ModelAndView modelAndView =null; 
		try {
			personDAO.adminLogin(username, password);
			request.getSession().setAttribute("name", username);
			modelAndView=new ModelAndView("redirect:admindashboard.jsp");
		} catch (InvalidCredentialException e) {
			modelAndView=new ModelAndView("redirect:adminlogin.jsp");
			modelAndView.addObject("message", e.getMessage());
		}
		return modelAndView;
	}
	
	// helper methods
	private ModelAndView rejectPrivileges(Map<String,Object> properties){
		ModelAndView mve = new ModelAndView("redirect:teacherdashboard.jsp");
		mve.addObject("message", "You do not have admin privileges");
		for(String key : properties.keySet()){
			mve.addObject(key, properties.get(key));
		}
		return mve;
	}
	
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
