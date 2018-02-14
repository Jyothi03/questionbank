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

/**
 * Tests for {@Person}.
 *
 */

@RunWith(JUnit4.class)
public class TestPerson {
	
	private Person p1;
	private Person p2;
	private Person p3;
	
	/**
	 * Create 3 Person instance
	 * Note: this method is called once before every test method
	 */
	@Before
	 public void setUp(){
		Set<Course> courses = new HashSet<Course>(0);
		this.p1 = new Person("bob", "william","user1","pass1", true, courses);
		this.p2 = new Person("sam", "roger","user2","pass2", false, courses);
		this.p3 = new Person("mike", "kiki","user3","pass3", false, courses);
		p1.setId(1);
		p2.setId(2);
		p3.setId(3);
	 }
	 
	 @Test
	 public void getId(){
		 assertEquals(new Integer(1),p1.getId());
		 assertEquals(new Integer(2),p2.getId());
		 assertEquals(new Integer(3),p3.getId());
	
	 }
	
	 @Test
	 public void getFirstName(){
		 assertEquals("bob", p1.getFirstName());
		 assertEquals("sam", p2.getFirstName());
		 assertEquals("mike", p3.getFirstName());
	
	 }
	 
	 @Test
	 public void getLastName(){
		 assertEquals("william", p1.getLastName());
		 assertEquals("roger", p2.getLastName());
		 assertEquals("kiki", p3.getLastName());
		 
	 }
	 
	 @Test
	 public void getUserName(){
		 assertEquals("user1", p1.getUserName());
		 assertEquals("user2", p2.getUserName());
		 assertEquals("user3", p3.getUserName());
		 
	 }
	 
	 @Test
	 public void getPassword(){
		 assertEquals("pass1", p1.getPassword());
		 assertEquals("pass2", p2.getPassword());
		 assertEquals("pass3", p3.getPassword());
		 
	 }
	
	
	
}
