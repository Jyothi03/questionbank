package org.questionBank.controller;

import org.questionBank.dao.QuestionDataUtil;
import org.questionBank.data.Answer;
import org.questionBank.data.Course;
import org.questionBank.data.Person;
import org.questionBank.data.Question;
import org.questionBank.exception.InvalidAnswerException;
import org.questionBank.exception.InvalidQuestionException;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.questionBank.dao.AnswerDataUtil;
import org.questionBank.dao.CourseDataUtil;
import org.questionBank.dao.PersonDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class QuestionController {
	
	@Autowired
	QuestionDataUtil questionDAO;
	@Autowired
	AnswerDataUtil answerDAO;
	@Autowired
	CourseDataUtil courseDAO;
	@Autowired
	PersonDataUtil personDAO;

	// Create
	@RequestMapping(value="/CourseAddQuestion",method=RequestMethod.GET)
	public ModelAndView addQuestionView(HttpServletRequest request,@RequestParam(required=false) Integer courseId, @RequestParam(required=false) String question,
										@RequestParam(required=false) String chapter, @RequestParam(required=false) String answerText){
		ModelAndView mve =  null;
		Course course = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid!=null)
		{
			Integer userId = (Integer) uid;
			Person p = personDAO.findPerson(userId);
			List<Course> courses = p.isAdmin() ? courseDAO.getCourses() : courseDAO.getCoursesForTeacher(userId);
			mve = new ModelAndView("views/questions/AddQuestion");
			mve.addObject("courses", courses);
			if(courseId != null)
			{
				course = courseDAO.findCourse(courseId);
			}
			
			Question q = questionDAO.populateQuestion(course, question, chapter);
//			Answer a = new Answer();
//			a.setAnswerText(answerText);
			mve.addObject("question", q);
//			mve.addObject("answer", a);
			if(question != null || chapter != null || answerText != null){
				mve.addObject("errors", questionDAO.questionErrors(q));
			}
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
		}
		else{
			return rejectInvalidUser(null);
		}		
		
		return mve;
	}

//	@Transactional
	@RequestMapping(value="/CourseAddQuestion",method=RequestMethod.POST)
	public ModelAndView createQuestion(HttpServletRequest request, @ModelAttribute("question") Question que, @RequestParam(required=false) Integer courseId, 
			@RequestParam(required=false) String question, @RequestParam(required=false) String chapter, @RequestParam(required=false) String answerText){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			try {
				Question newQuestion = questionDAO.createQuestion(que);
				Answer newAnswer = answerDAO.createAnswer(newQuestion,answerText);
				mve=new ModelAndView("redirect:ShowQuestion?id="+newQuestion.getId());
				mve.addObject("question", newQuestion);
				mve.addObject("answer",newAnswer);
			} 
			catch (InvalidQuestionException qe) {
				mve=new ModelAndView("redirect:CourseAddQuestion");
//				Integer courseId = que.getCourse() == null ? null : que.getCourse().getId();
				mve.addObject("courseId", courseId);
				mve.addObject("question", que.getQuestion());
				mve.addObject("chapter", que.getChapter());
				mve.addObject("answerText", answerText);
			}
			catch (InvalidAnswerException ae) {
				mve=new ModelAndView("redirect:CourseAddQuestion");
//				Integer courseId = que.getCourse() == null ? null : que.getCourse().getId();
				mve.addObject("courseId", courseId);
				mve.addObject("question", que.getQuestion());
				mve.addObject("chapter", que.getChapter());
				mve.addObject("answerText", answerText);		
			}
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}

	
	
	// Show
	@RequestMapping(value="/ShowQuestion",method=RequestMethod.GET)
	public ModelAndView showQuestion(HttpServletRequest request, @RequestParam("id") int id){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			mve = new ModelAndView("views/questions/ShowQuestion");
			Question q = questionDAO.findQuestion(id);
			List<Answer> answers = answerDAO.findAnswersByQuestionId(q.getId());
			Answer a = null;
			if(answers.isEmpty())
			{
				a = new Answer();
			}
			else
			{
				a = answers.get(0);
			}
			Course c = courseDAO.findCourse(q.getCourse().getId());
			q.setCourse(c);
			mve.addObject("question",q);
			mve.addObject("answer",a);
			mve.addObject("errors", questionDAO.questionErrors(q));
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	@RequestMapping(value="/ViewQuestion",method=RequestMethod.GET)
	public ModelAndView viewQuestion(HttpServletRequest request, @RequestParam("id") int id){
		ModelAndView mve = null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			mve = new ModelAndView("views/questions/ViewQuestion");
			Course c = courseDAO.findCourse(id);
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
	
	@RequestMapping(value="/TeacherQuestionView",method=RequestMethod.GET)
		public ModelAndView listCourses(HttpServletRequest request){
			ModelAndView mve =  null;
			HttpSession s = request.getSession();
			Object uid = s.getAttribute("userId");
			if(uid != null){
				Integer userId = (Integer) uid;
				List<Map<String,Object>> courses = courseDAO.getDataForTeacherCourses(userId);
				mve = new ModelAndView("views/questions/teacherquestionview");
				mve.addObject("courses", courses);
				Person curUser = personDAO.findPerson(userId);
				mve.addObject("isAdmin", curUser.isAdmin());
				return mve;
			}else{
				return rejectInvalidUser(null);
			}
		}
		
	   
		
	// Edit
	@RequestMapping(value="/EditQuestion",method=RequestMethod.GET)
	public ModelAndView editQuestion(HttpServletRequest request, @RequestParam("id") int id,@RequestParam("answerId") int answerId){
		ModelAndView mve =  null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			mve = new ModelAndView("views/questions/EditQuestion");
			Question q = questionDAO.findQuestion(id);
			Answer a = answerDAO.findAnswer(answerId);
			try{
				questionDAO.validateQuestion(q);
				answerDAO.validateAnswer(a);
			}catch(InvalidQuestionException ex){
				mve.addObject("errors", questionDAO.questionErrors(q));
			}catch(InvalidAnswerException ex){
				mve.addObject("errors", answerDAO.answerErrors(a));
			}
			List<Course> courses = courseDAO.getCourses();
			mve.addObject("question",q);
			mve.addObject("answer",a);
			mve.addObject("courses", courses);
			Integer userId = (Integer) uid;
			Person curUser = personDAO.findPerson(userId);
			mve.addObject("isAdmin", curUser.isAdmin());
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}

	@RequestMapping(value="/UpdateQuestion",method=RequestMethod.POST)
	public ModelAndView updateQuestion(HttpServletRequest request, @ModelAttribute("question") Question question, @RequestParam(required=false) Integer answerId, @RequestParam(required=false) String answerText){
		ModelAndView mve =  null;
		HttpSession s = request.getSession();
		Object uid = s.getAttribute("userId");
		if(uid != null){
			try {
				questionDAO.updateQuestion(question);
				answerDAO.updateAnswer(answerId, answerText);
				Answer newAnswer = answerDAO.findAnswer(answerId);
				mve=new ModelAndView("redirect:ShowQuestion?id="+question.getId());
				mve.addObject("question", question);
				mve.addObject("answer", newAnswer);
			} catch (InvalidQuestionException e) {
				mve=new ModelAndView("redirect:EditQuestion?id="+question.getId()+"&answerId="+answerId);
			}
			catch (InvalidAnswerException ex) {
				mve=new ModelAndView("redirect:EditQuestion?id="+question.getId()+"&answerId="+answerId);
			}
			return mve;
		}else{
			return rejectInvalidUser(null);
		}
	}
	
	// Helper
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
