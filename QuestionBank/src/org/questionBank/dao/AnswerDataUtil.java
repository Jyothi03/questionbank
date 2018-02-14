package org.questionBank.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.questionBank.data.Answer;
import org.questionBank.data.Question;
import org.questionBank.exception.InvalidAnswerException;
import org.questionBank.home.AnswerHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AnswerDataUtil {

	private static final Logger log = LogManager.getLogger(AnswerDataUtil.class);
	
	public AnswerDataUtil(){ }

	@Autowired
	private AnswerHome ah = new AnswerHome();
	
 private static String QUESTIONID_ERROR = "Invalid Question Selected.";
	private static int MIN_ANSWERTEXT_LENGTH = 1;
	private static int MAX_ANSWERTEXT_LENGTH = 256;
	private static String MIN_ANSWERTEXT_LENGTH_ERROR = "You must provide an answer.";
	private static String MAX_ANSWERTEXT_LENGTH_ERROR = "Answer must be at most "+MAX_ANSWERTEXT_LENGTH+" characters long.";
 
	@Transactional
	public Answer createAnswer(Question question,String answerText) throws InvalidAnswerException {
		
		Answer a = new Answer();
		//a.setId(id);
		a.setQuestion(question);
		a.setAnswerText(answerText);

		validateAnswer(a);
		// Save Answer to DB
		log.info("Creating Answer");
		log.debug(describeAnswer(a));
		ah.persist(a);
		return a;
	}
	
	public String describeAnswer(Answer answer){
		String str = "Answer ["+answer.getId()+"]:\r\n";
		str += "- Id: ["+answer.getId()+"]\r\n";
		String questionName = answer.getQuestion() == null ? "null" : answer.getQuestion().getId() + "";
		str += "- QuestionID: ["+questionName+"]\r\n";
		str += "- Answer: ["+answer.getAnswerText()+"]\r\n";
		return str;
	}
	
public void deleteAnswer(Integer id){
		// TODO: test this
		Answer del = ah.findById(id); 
		ah.remove(del);
	}
	
	public Answer findAnswer(Integer id){
		// TODO: test this
	return ah.findById(id);
	}
	
	public List<Answer> findAnswersByQuestionId(Integer questionId){
		// TODO: test this
	return ah.findByQuestionId(questionId);
	}
	

	public boolean updateAnswer(Integer answerId,String answerText) throws InvalidAnswerException {
		try{
			Answer a = findAnswer(answerId);
//			a.setQuestionId(questionId);
			a.setAnswerText(answerText);
	//		a.setId(id);
			validateAnswer(a);
			ah.merge(a);
			return true;
		}catch(RuntimeException re){
			List<String> err = new ArrayList<String>();
			err.add(re.getMessage());
			throw new InvalidAnswerException(err);
		}
	} 
	
/*	public boolean updateQuestion(Integer id, Integer courseId, String question, String chapter) throws InvalidQuestionException {
		try{
			Question q = findQuestion(id);
			q.setCourseId(courseId);
			q.setQuestion(question);
			q.setChapter(chapter);
			validateQuestion(q);
			qh.merge(q);
			return true;
		}catch(RuntimeException re){
			List<String> err = new ArrayList<String>();
			err.add(re.getMessage());
			throw new InvalidQuestionException(err);
		}
	} */
	
	public List<Answer> getAnswer(){
		// TODO: implement this
		return new ArrayList<Answer>();
		
	}
	
	public Answer getAnswerForQuestion(Question q){
		Integer qid = q.getId();
		List<Answer> answer = ah.findByQuestionId(qid);
		if(answer.size() != 0)
			return answer.get(0);
		else
			return null;
	}
	
	public List<Map<String,Object>> getDataForQuestionAnswers(Integer questionId){
		List<Answer> answers = ah.findByQuestionId(questionId);
		return getAnswersData(answers);
	}
	
	public List<Map<String,Object>> getAnswersData(List<Answer> answers){
		List<Map<String,Object>> AnswerData = new ArrayList<Map<String,Object>>();
		for(Answer answer : answers){
			AnswerData.add(mapAnswer(answer));
		}
		return AnswerData;
	}
	
	public Map<String,Object> mapAnswer(Answer answer){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", answer.getId());
//		map.put("questionId", answer.getQuestionId());
		map.put("question", answer.getQuestion());
		map.put("answer", answer.getAnswerText());
		return map;
	}
	
	public List<String> answerErrors(Answer answer) {
		return getAnswerErrors(answer);
	}
	
	private List<String> getAnswerErrors(Answer answer){
		List<String> errors = new ArrayList<String>();
		
//		if(answer.getQuestionId() == null)
		if(answer.getQuestion() == null)
		 errors.add(QUESTIONID_ERROR);
		//else if(question.getChapter().length() > MAX_CHAPTER_LENGTH)
		//	errors.add(MAX_CHAPTER_LENGTH_ERROR);
		if(answer.getAnswerText() == null || answer.getAnswerText().length() < MIN_ANSWERTEXT_LENGTH)
			errors.add(MIN_ANSWERTEXT_LENGTH_ERROR);
		else if(answer.getAnswerText().length() > MAX_ANSWERTEXT_LENGTH)
			errors.add(MAX_ANSWERTEXT_LENGTH_ERROR);
		return errors;
	}
	
	public void validateAnswer(Answer answer) throws InvalidAnswerException {
		List<String> errors = getAnswerErrors(answer);
		if(!errors.isEmpty()){
			throw new InvalidAnswerException(errors);
		}
	}
}
