package org.questionBank.home;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.questionBank.data.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentHome {

	private static final Log log = LogFactory.getLog(DepartmentHome.class);

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public List<Department> getDepartments(){
		TypedQuery<Department> q = entityManager.createQuery("select d from Department d", Department.class);
		List<Department> results = q.getResultList(); 
		return results;
	}
	
	@Transactional
	public void persist(Department transientInstance) {
		log.debug("persisting Department instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(Department persistentInstance) {
		log.debug("removing Department instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public Department merge(Department detachedInstance) {
		log.debug("merging Department instance");
		try {
			Department result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Transactional
	public Department findById(Integer id) {
		log.debug("getting Department instance with id: " + id);
		try {
			Department instance = entityManager.find(Department.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
