package org.questionBank.pdf;

import com.itextpdf.text.DocumentException;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.questionBank.data.Course;
import org.questionBank.data.Question;

public class BuildQuestionaire {
 
    public static void main(String[] args) throws IOException, DocumentException {
    	// Get Entity Manager
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("questionBank");
    	EntityManager em = emf.createEntityManager();
        Integer course1Id = 1;
        Course course1 = getCourse(em, course1Id);
        Integer course2Id = 4;
        Course course2 = getCourse(em, course2Id);
        List<Question> questionAndAnswer = getSampleCourseQuestions(em, course1.getId());
        List<Question> questions = getSampleQuestions(em);
        // Run PDF generator
        String title1 = getCourseTitle(course1);
        String title2 = getCourseTitle(course2);
        File file1 = new QuestionairePDFGenerator().createQAndAPdfForQuestions(title1, questionAndAnswer);
        File file2 = new QuestionairePDFGenerator().createQuestionPDFForQuestions(title2, questions);
        Desktop.getDesktop().open(file1);
        Desktop.getDesktop().open(file2);
        System.exit(0);
    }
    
    private static String getCourseTitle(Course course){
    	String title = course.getCourseName();
    	return title;
    }
    
    private static Course getCourse(EntityManager em, Integer courseId){
    	Course course = em.find(Course.class, courseId);
    	return course;
    }
    
    private static List<Question> getSampleCourseQuestions(EntityManager em, Integer courseId){
		TypedQuery<Question> q = em.createQuery("select q from Question q where course.id = :courseId", Question.class);
	    q.setParameter("courseId", 1);
		List<Question> results = q.getResultList(); 
		return results;
    }
 
    private static List<Question> getSampleQuestions(EntityManager em){
    	int[] ids = new int[]{7,8,10,12};
    	List<Question> questions = new ArrayList<Question>();
    	for (int id :ids){
			Question q = em.find(Question.class, id);
			questions.add(q);
    	}
    	return questions;
    }

}
