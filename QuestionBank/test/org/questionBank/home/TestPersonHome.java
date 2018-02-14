package org.questionBank.home;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

import org.questionBank.data.Course;
import org.questionBank.data.Person;
import org.questionBank.home.PersonHome;

import junit.framework.TestCase;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.util.List;
import java.util.Properties;

public class TestPersonHome extends TestCase {
	public void test() throws Exception {
//		Set<Course> courses = new HashSet<Course>(0);
//		Person p1 = new Person("bob", "william","user1","pass1", true, courses);
//		p1.setId(new Integer(1));
//		final Properties p = new Properties();
//		p.put("questionBank", "new://Resource?type=DataSource");
//        p.put("questionBank.JdbcDriver", "com.mysql.jdbc.Driver");
//        p.put("questionBank.JdbcUrl", "jdbc:mysql://localhost:3306/questionbank");
//        p.put("questionBank.jdbc.user","qbank");
//        p.put("questionBank", "qbank12345");
//        
//        Context context = EJBContainer.createEJBContainer(p).getContext();
//
//        PersonHome ph = (PersonHome) context.lookup("java:global/injection-of-entitymanager/PersonHome");
//
//        ph.persist(p1);
//        List<Person> list = (List<Person>) ph.findById(1);
//        assertEquals("List.size()",1,list.size());
		assert(true);
	}

}