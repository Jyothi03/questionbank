package org.questionBank.hibernate;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.questionBank.data.Department;

public class MySQLTest {

	private static final Logger log = LogManager.getLogger(MySQLTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Department dept = new Department();
		String compSciName = "Computer Science";
		String compSciAbbr = "CSCI";
		dept.setAbbreviation(compSciAbbr);
		dept.setName(compSciName);
		
		log.info("Creating Department");

		session.beginTransaction();
		session.save(dept);
		session.getTransaction().commit();
	}

}
