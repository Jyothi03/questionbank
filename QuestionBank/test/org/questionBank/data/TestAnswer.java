package org.questionBank.data;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.questionBank.data.Answer;
import org.questionBank.data.Course;
import org.questionBank.data.Person;
import org.questionBank.data.Question;

/**
 * Tests for {@Answer}.
 *
 */

@RunWith(JUnit4.class)
public class TestAnswer {

	//Departments
	private Department d1;
	private Department d2;
	// Questions
	private Question q1;
	private Question q2;
	private Question q3;
	// Courses
	private Course c1;
	private Course c2;
	private Course c3;
	// Answers
	private Answer a1;
	private Answer a2;
	private Answer a3;
	
	private void createDepartments(){
		// Create Departments
		Set<Course> courses = new HashSet<Course>(10);
		this.d1 = new Department("Computer Science", "CSCI", courses);
		this.d2 = new Department("Mathematics", "MATH", courses);
		d1.setId(1);
		d2.setId(2);
	}
	
	private void createCourses(){
		// Create Courses
		Set<Person> people = new HashSet<Person>(10);
		Set<Question> questions = new HashSet<Question>(10);
		this.c1 = new Course(d2, "Calculus", "100",3, people, questions);
		this.c2 = new Course(d1, "Agile", "6363",2, people, questions);
		this.c3 = new Course(d1, "DataBase", "5225",1, people, questions);
		c1.setId(1);
		c2.setId(2);
		c3.setId(3);
	}
	
	private void createQuestions(){
		// Create Questions
		Set<Answer> answers = new HashSet<Answer>(0);
		this.q1 = new Question(c1, "question1", "ch1", answers);
		this.q2 = new Question(c2, "question2", "ch2", answers);
		this.q3 = new Question(c3, "question3", "ch3", answers);
		q1.setId(1);
		q2.setId(2);
		q3.setId(3);
	}
	
	/**
	 * Create 3 Answer instance
	 * Note: this method is called once before every test method
	 */
	@Before
	 public void setUp(){
		createDepartments();
		createCourses();
		createQuestions();
		this.a1 = new Answer(q1, "answer1");
		this.a2 = new Answer(q2, "answer2");
		this.a3 = new Answer(q3, "answer3");
		a1.setId(10);
		a2.setId(11);
		a3.setId(12);

	 }
	 
	 @Test
	 public void getId(){
		 assertEquals(new Integer(10), a1.getId());
		 assertEquals(new Integer(11), a2.getId());
		 assertEquals(new Integer(12), a3.getId());
	
	 }
	
	 @Test
	 public void getQuestionId(){
		 assertEquals(q1, a1.getQuestion());
		 assertEquals(q2, a2.getQuestion());
		 assertEquals(q3, a3.getQuestion());
	
	 }
	 
	 @Test
	 public void getAnswerText(){
		 assertEquals("answer1", a1.getAnswerText());
		 assertEquals("answer2", a2.getAnswerText());
		 assertEquals("answer3", a3.getAnswerText());
		 
	 }
	 
	 
	
	
}
