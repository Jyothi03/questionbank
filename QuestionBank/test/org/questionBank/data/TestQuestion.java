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
 * Tests for {@Question}.
 *
 */

@RunWith(JUnit4.class)
public class TestQuestion {

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
	
	private void createDepartments(){
		// Create Departments
		Set<Course> courses = new HashSet<Course>(10);
		this.d1 = new Department("Computer Science", "CSCI", courses);
		this.d2 = new Department("Mathematics", "MATH", courses);
		d1.setId(1);
		d2.setId(2);
	}
	
	/**
	 * Create 3 Question instance
	 * Note: this method is called once before every test method
	 */
	@Before
	 public void setUp(){
		createDepartments();
		// Create Courses
		Set<Person> people = new HashSet<Person>(10);
		Set<Question> questions = new HashSet<Question>(10);
		this.c1 = new Course(d2, "Calculus", "100",3, people, questions);
		this.c2 = new Course(d1, "Agile", "6363",2, people, questions);
		this.c3 = new Course(d1, "DataBase", "5225",1, people, questions);
		c1.setId(1);
		c2.setId(2);
		c3.setId(3);
		// Create Questions
		Set<Answer> answers = new HashSet<Answer>(0);
		this.q1 = new Question(c1, "question1", "ch1", answers);
		this.q2 = new Question(c2, "question2", "ch2", answers);
		this.q3 = new Question(c3, "question3", "ch3", answers);
		q1.setId(1);
		q2.setId(2);
		q3.setId(3);
	 }
	 
	 @Test
	 public void getId(){
		 assertEquals(new Integer(1), q1.getId());
		 assertEquals(new Integer(2), q2.getId());
		 assertEquals(new Integer(3), q3.getId());
	
	 }
	
	 @Test
	 public void getCourseId(){
		 assertEquals(c1 ,q1.getCourse());
		 assertEquals(c2 ,q2.getCourse());
		 assertEquals(c3 ,q3.getCourse());
	
	 }
	 
	 @Test
	 public void getQuestion(){
		 assertEquals("question1",q1.getQuestion());
		 assertEquals("question2",q2.getQuestion());
		 assertEquals("question3",q3.getQuestion());
	 }
	 
	 @Test
	 public void getChapter(){
		 assertEquals("ch1",q1.getChapter());
		 assertEquals("ch2",q2.getChapter());
		 assertEquals("ch3",q3.getChapter());
	 }
	
	
	
}
