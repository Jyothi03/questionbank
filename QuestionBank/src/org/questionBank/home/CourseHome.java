package org.questionBank.home;
// Generated Oct 9, 2016 11:50:10 PM by Hibernate Tools 5.2.0.Beta1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.questionBank.data.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class Course.
 * @see org.questionBank.data.Course
 * @author Hibernate Tools
 */


@Service
@Transactional
public class CourseHome {

	private static final Log log = LogFactory.getLog(CourseHome.class);

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public List<Course> getCourses(){
		TypedQuery<Course> q = entityManager.createQuery("select c from Course c", Course.class);
		List<Course> results = q.getResultList(); 
		return results;
	}
	
	public List<Course> getCoursesForPersonId(Integer userId){
		TypedQuery<Course> q = entityManager.createQuery("select c from Course c join c.persons p where p.id = :userId", Course.class);
	    q.setParameter("userId", userId);
	    return q.getResultList();
	}
	
	@Transactional
	public void persist(Course transientInstance) {
		log.debug("persisting Course instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(Course persistentInstance) {
		log.debug("removing Course instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public Course merge(Course detachedInstance) {
		log.debug("merging Course instance");
		try {
			Course result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Transactional
	public Course findById(Integer id) {
		log.debug("getting Course instance with id: " + id);
		try {
			Course instance = entityManager.find(Course.class, id);
			instance.setDepartment(instance.getDepartment());
			instance.setQuestions(instance.getQuestions());
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
}
