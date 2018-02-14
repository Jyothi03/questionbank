package org.questionBank.home;
// Generated Oct 9, 2016 11:50:10 PM by Hibernate Tools 5.2.0.Beta1
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.questionBank.data.Answer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class Answer.
 * @see org.questionBank.dataOLD.Answer
 * @author Hibernate Tools
 */
@Service
@Transactional
public class AnswerHome {

	private static final Log log = LogFactory.getLog(AnswerHome.class);

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

    public List<Answer> findByQuestionId(Integer questionId) {
    	log.debug("getting Answer with Question Id: " + questionId);
		String str= "select a from Answer a where a.question.id=" +questionId; 
    	TypedQuery<Answer> q = entityManager.createQuery(str,Answer.class);
		List<Answer> res = q.getResultList();
		return res;
	}


    @Transactional
	public void persist(Answer transientInstance) {
		log.debug("persisting Answer instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

    @Transactional
    public void remove(Answer persistentInstance) {
		log.debug("removing Answer instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

    @Transactional
    public Answer merge(Answer detachedInstance) {
		log.debug("merging Answer instance");
		try {
			Answer result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
    
    public Answer findById(Integer id)
    {
    	log.debug("getting Aswer instance with id: "+id);
    	try
    	{
    		Answer instance = entityManager.find(Answer.class, id);
    		log.debug("get successful");
    		return instance;
    	}
    	catch(RuntimeException re)
    	{
    		log.error("get failed", re);
    		throw re;
    	}
    	
    	
    }

}
