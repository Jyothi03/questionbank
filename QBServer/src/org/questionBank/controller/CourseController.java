package org.questionBank.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.questionBank.dao.CourseDataUtil;
import org.questionBank.dao.DepartmentDataUtil;
import org.questionBank.dao.PersonDataUtil;
import org.questionBank.dao.QuestionDataUtil;
import org.questionBank.data.Answer;
import org.questionBank.data.Course;
import org.questionBank.data.Department;
import org.questionBank.data.Person;
import org.questionBank.dao.AnswerDataUtil;
import org.questionBank.exception.InvalidCourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CourseController {

	@Autowired
	PersonDataUtil personDAO;
	@Autowired
	CourseDataUtil courseDAO;
	@Autowired
	QuestionDataUtil questionDAO;
	@Autowired
	AnswerDataUtil answerDAO;
	@Autowired
	DepartmentDataUtil departmentDAO;

	// List
	@RequestMapping(value="/TeacherCourseView",method=RequestMethod.GET)
	public ModelAndView listCourses(HttpServletRequest request){
		ModelAndView mve =  null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Integer userId = (Integer) uid;
			List<Map<String,Object>> courses = courseDAO.getDataForTeacherCourses(userId);
			mve = new ModelAndView("views/courses/teachercourseview");
			mve.addObject("courses", courses);
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	@RequestMapping(value="/AdminCourseView",method=RequestMethod.GET)
	public ModelAndView listAllCourses(HttpServletRequest request){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			List<Map<String,Object>> courses = courseDAO.getDataForAllCourses();
			mve = new ModelAndView("views/courses/teachercourseview");
			mve.addObject("courses", courses);
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}

	// Create
	@RequestMapping(value="/TeacherAddCourse",method=RequestMethod.GET)
	public ModelAndView addCoursesView(HttpServletRequest request, @RequestParam(required=false) String courseName, @RequestParam(required=false) String courseNumber,
			 @RequestParam(required=false) Integer credit, @RequestParam(required=false) Integer departmentId){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Course c = null;
			boolean newCourse = true;
			Department dept = departmentId == null ? null : departmentDAO.findDepartment(departmentId);
			if(courseName != null || courseNumber != null || dept != null || credit != null){
				c = courseDAO.populateCourse(courseName, courseNumber, dept, credit);
				newCourse = false;
			}else{
				c = new Course();
			}
			List<Department> departments = departmentDAO.getDepartments();
			mve = new ModelAndView("views/courses/AddCourse");
			mve.addObject("course", c);
			mve.addObject("departments",departments);
			if(!newCourse)
				mve.addObject("errors", courseDAO.courseErrors(c));
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}

	@RequestMapping(value="/TeacherAddCourse",method=RequestMethod.POST)
	public ModelAndView createCourse(HttpServletRequest request, @ModelAttribute("course") Course course){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			try {
				Course newCourse;
				Integer userId = (Integer) uid;
				Person p = personDAO.findPerson(userId);
				if(p.isAdmin()){
					newCourse = courseDAO.createCourse(course);
				}else{
					newCourse = courseDAO.createCourseForTeacher(userId, course);
				}
				mve=new ModelAndView("redirect:ShowCourse?id="+newCourse.getId());
				mve.addObject("course", newCourse);
			} catch (InvalidCourseException e) {
				mve= refreshTeacherAddCourse(course);
			}
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	// Show
	@RequestMapping(value="/ShowCourse",method=RequestMethod.GET)
	public ModelAndView showCourse(HttpServletRequest request, @RequestParam("id") int id){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			mve = new ModelAndView("views/courses/ShowCourse");
			Course c = courseDAO.findCourse(id);
			Department d = c.getDepartment() == null ? new Department() : departmentDAO.findDepartment(c.getDepartment().getId());
			c.setDepartment(d);
			List<Map<String,Object>> questions = questionDAO.getDataForCourseQuestions(c);
			for(Map<String,Object> question : questions)
			{
				Object qid = question.get("id");
				if(qid != null && (qid instanceof Integer) )
				{
					int questionId = (Integer)qid;
					List<Answer> answers = answerDAO.findAnswersByQuestionId(questionId);
					if(answers.isEmpty())
					{
						question.put("answer", "");
					}
					else if(answers.size() > 1)
					{ 
						question.put("answer", "Multiple Answers");
					}
					else
					{ 
						Answer a = answers.get(0);
						question.put("answer", a.getAnswerText());
						question.put("answerId", a.getId());
					}
				}
			}
		
			mve.addObject("course",c);
			mve.addObject("questions",questions);
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	// Edit
	@RequestMapping(value="/EditCourse",method=RequestMethod.GET)
	public ModelAndView editCourse(HttpServletRequest request, @RequestParam("id") int id, @RequestParam(required=false) String errors){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			mve = new ModelAndView("views/courses/EditCourse");
			Course c = courseDAO.findCourse(id);
			List<Department> departments = departmentDAO.getDepartments();
			try{
				courseDAO.validateCourse(c);
			}catch(InvalidCourseException ex){
				mve.addObject("errors", courseDAO.courseErrors(c));
			}
			if(errors != null && !errors.trim().isEmpty())
				mve.addObject("errors", errors);
			mve.addObject("course",c);
			mve.addObject("departments", departments);
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}

	@RequestMapping(value="/UpdateCourse",method=RequestMethod.POST)
	public ModelAndView updateCourse(HttpServletRequest request, @ModelAttribute("course") Course course){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			Integer id = course.getId();
			try {
				courseDAO.updateCourse(course);
				Course newCourse = courseDAO.findCourse(id);
				mve=new ModelAndView("redirect:ShowCourse?id="+id);
				mve.addObject("course", newCourse);
			} catch (InvalidCourseException e) {
				mve=new ModelAndView("redirect:EditCourse?id="+id);
				mve.addObject("errors", courseDAO.courseErrors(course));
			}
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	private ModelAndView refreshTeacherAddCourse(Course course){
		ModelAndView mve=new ModelAndView("redirect:TeacherAddCourse");
		if(course != null){
			mve.addObject("courseName", course.getCourseName());
			mve.addObject("courseNumber", course.getCourseNumber());
			if(course.getDepartment() != null)
				mve.addObject("departmentId", course.getDepartment().getId());
			mve.addObject("credit", course.getCredit());
		}
		return mve;
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
