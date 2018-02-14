package org.questionBank.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.questionBank.data.Course;
import org.questionBank.data.Question;
import org.questionBank.exception.InvalidAnswerException;
import org.questionBank.exception.InvalidQuestionException;
import org.questionBank.home.QuestionHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class QuestionDataUtil {

	private static final Logger log = LogManager.getLogger(QuestionDataUtil.class);
	
	public QuestionDataUtil(){ }

	@Autowired
	private QuestionHome qh = new QuestionHome();
	
	private static String COURSE_ID_ERROR = "Invalid Course Selected.";
	private static int MIN_CHAPTER_LENGTH = 1;
	private static int MAX_CHAPTER_LENGTH = 7;
	private static String MIN_CHAPTER_LENGTH_ERROR = "Question Chapter value must be at least "+MIN_CHAPTER_LENGTH+" characters long.";
	private static String MAX_CHAPTER_LENGTH_ERROR = "Question Chapter value must be at most "+MAX_CHAPTER_LENGTH+" characters long.";
	private static int MIN_QUESTION_LENGTH = 1;
	private static int MAX_QUESTION_LENGTH = 256;
	private static String MIN_QUESTION_LENGTH_ERROR = "Question must be at least "+MIN_QUESTION_LENGTH+" characters long.";
	private static String MAX_QUESTION_LENGTH_ERROR = "Question must be at most "+MAX_QUESTION_LENGTH+" characters long.";
	
	public Question populateQuestion(Course course, String question, String chapter){
		Question q = new Question();
		q.setCourse(course);
		q.setQuestion(question);
		q.setChapter(chapter);
		return q;
	}

	@Transactional
	public Question createQuestion(Question que) throws InvalidQuestionException, InvalidAnswerException {
//		Question q = populateQuestion(course, question, chapter);
		validateQuestion(que);
		// Save Course to DB
		log.info("Creating Course");
		log.debug(describeQuestion(que));
		qh.persist(que);
		return que;
	}
	
	public String describeQuestion(Question question){
		String str = "Question ["+question.getId()+"]:\r\n";
		str += "- Id ["+question.getId()+"]\r\n";
		String courseName = question.getCourse() == null ? "null" : question.getCourse().getId() + ""; 
		str += "- Course: ["+courseName+"]\r\n";
		str += "- Question: ["+question.getQuestion()+"]\r\n";
		str += "- Chapter: ["+question.getChapter()+"]\r\n";
		return str;
	}
	
	public void deleteQuestion(Integer id){
		// TODO: test this
		Question del = qh.findById(id); 
		qh.remove(del);
	}
	
	public Question findQuestion(Integer id){
		// TODO: test this
		Question q = qh.findById(id);
		q.getCourse();
		return q;
	}
	
	public boolean updateQuestion(Question q) throws InvalidQuestionException {
		try{
			validateQuestion(q);
			qh.merge(q);
			return true;
		}catch(RuntimeException re){
			List<String> err = new ArrayList<String>();
			err.add(re.getMessage());
			throw new InvalidQuestionException(err);
		}
	}
	
	public List<Question> getQuestions(){
		// TODO: implement this
		return new ArrayList<Question>();
		
	}
	
	public List<Question> getQuestionsForCourse(Course course){
		List<Question> questions = qh.getQuestionsForCourse(course);
		return questions;
	}
	
	public List<Map<String,Object>> getDataForCourseQuestions(Course course){
		List<Question> questions = getQuestionsForCourse(course);
		return getQuestionsData(questions);
	}
	
	public List<Map<String,Object>> getQuestionsData(List<Question> questions){
		List<Map<String,Object>> questionData = new ArrayList<Map<String,Object>>();
		for(Question question : questions){
			questionData.add(mapQuestion(question));
		}
		return questionData;
	}
	
	public Map<String,Object> mapQuestion(Question question){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", question.getId());
		map.put("course", question.getCourse());
		map.put("chapter", question.getChapter());
		map.put("question", question.getQuestion());
		return map;
	}
	
	public List<String> questionErrors(Question question) {
		return getQuestionErrors(question);
	}
	
	private List<String> getQuestionErrors(Question question){
		List<String> errors = new ArrayList<String>();
		if(question.getCourse() == null)
			errors.add(COURSE_ID_ERROR);
		if(question.getChapter() == null || question.getChapter().length() < MIN_CHAPTER_LENGTH)
			errors.add(MIN_CHAPTER_LENGTH_ERROR);
		else if(question.getChapter().length() > MAX_CHAPTER_LENGTH)
			errors.add(MAX_CHAPTER_LENGTH_ERROR);
		if(question.getQuestion() == null || question.getQuestion().length() < MIN_QUESTION_LENGTH)
			errors.add(MIN_QUESTION_LENGTH_ERROR);
		if(question.getQuestion().length() > MAX_QUESTION_LENGTH)
			errors.add(MAX_QUESTION_LENGTH_ERROR);
		return errors;
	}
	
	public void validateQuestion(Question question) throws InvalidQuestionException {
		List<String> errors = getQuestionErrors(question);
		if(!errors.isEmpty()){
			throw new InvalidQuestionException(errors);
		}
	}
}
