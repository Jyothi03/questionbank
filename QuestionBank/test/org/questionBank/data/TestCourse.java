package org.questionBank.data;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.questionBank.data.Course;
import org.questionBank.data.Person;
import org.questionBank.data.Question;

/**
 * Tests for {@Course}.
 *
 */

@RunWith(JUnit4.class)
public class TestCourse {

	//Departments
	private Department d1;
	private Department d2;
	//Courses
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
	 * Create 3 Course instance
	 * Note: this method is called once before every test method
	 */
	@Before
	 public void setUp(){
		createDepartments();
		Set<Person> people = new HashSet<Person>(10);
		Set<Question> questions = new HashSet<Question>(10);
		this.c1 = new Course(d2, "Calculus", "100",3, people, questions);
		this.c2 = new Course(d1, "Agile", "6363",2, people, questions);
		this.c3 = new Course(d1, "DataBase", "5225",1, people, questions);
		c1.setId(1);
		c2.setId(2);
		c3.setId(3);

	 }
	 
	 @Test
	 public void getId(){
		 assertEquals(new Integer(1), c1.getId());
		 assertEquals(new Integer(2), c2.getId());
		 assertEquals(new Integer(3), c3.getId());
	
	 }
	
	 @Test
	 public void getCourseName(){
		 assertEquals("Calculus", c1.getCourseName());
		 assertEquals("Agile", c2.getCourseName());
		 assertEquals("DataBase", c3.getCourseName());
	
	 }
	 
	 @Test
	 public void getCourseNumber(){
		 assertEquals("100", c1.getCourseNumber());
		 assertEquals("6363", c2.getCourseNumber());
		 assertEquals("5225", c3.getCourseNumber());
		 
	 }
	 
	 @Test
	 public void getDepartment(){
		 assertEquals(d2,c1.getDepartment());
		 assertEquals(d1, c2.getDepartment());
		 assertEquals(d1, c3.getDepartment());
		 
	 }
	 
	 
	 @Test
	 public void getCredit(){
		 assertEquals(new Integer(3), c1.getCredit());
		 assertEquals(new Integer(2), c2.getCredit());
		 assertEquals(new Integer(1), c3.getCredit());
	 }
	
	
	
}
