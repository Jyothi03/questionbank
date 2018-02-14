package org.questionBank.home;
// Generated Oct 9, 2016 11:50:10 PM by Hibernate Tools 5.2.0.Beta1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.questionBank.data.Person;
//import org.questionBank.exception.InvalidCredentialException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class Person.
 * @see org.questionBank.dataOLD.Person
 * @author Hibernate Tools
 */


@Service
@Transactional
public class PersonHome {

	private static final Log log = LogFactory.getLog(PersonHome.class);

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager transactionManager;
	@Transactional
	public void persist(Person transientInstance) {
		log.debug("persisting Person instance");
		try {
			transactionManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	@Transactional
	public void remove(Person persistentInstance) {
		log.debug("removing Person instance");
		try {
			transactionManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}
	@Transactional
	public Person merge(Person detachedInstance) {
		log.debug("merging Person instance");
		try {
			Person result = transactionManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	@Transactional
	public Person findById(Integer id) {
		log.debug("getting Person instance with id: " + id);
		try {
			Person instance = transactionManager.find(Person.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Person> getUsers(){
		TypedQuery<Person> q = transactionManager.createQuery("select u from Person u",Person.class);
		List<Person> results = q.getResultList();
		return results;
	}
	
	public List<Person> findUsersByUserName(String userName){
		String sql = "select p from Person p where userName = :userName";
		TypedQuery<Person> q = transactionManager.createQuery(sql, Person.class)
				.setParameter("userName",userName);
		List<Person> results = q.getResultList();
		return results;
	}

//	public Person findByUserName(String userName) throws InvalidCredentialException {
//		List<Person> people = findUsersByUserName(userName);
//		if(people==null || people.isEmpty()){
//			throw new InvalidCredentialException("invalid username");
//		}
//		if(people.size() > 1){
//			throw new InvalidCredentialException("multiple users found");
//		}
//		Person person  = people.get(0);
//		return person;
//	}
}
