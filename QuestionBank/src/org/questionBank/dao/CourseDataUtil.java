package org.questionBank.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.questionBank.data.Course;
import org.questionBank.data.Department;
import org.questionBank.data.Person;
import org.questionBank.exception.InvalidCourseException;
import org.questionBank.home.CourseHome;
import org.questionBank.home.PersonHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CourseDataUtil {

	private static final Logger log = LogManager.getLogger(CourseDataUtil.class);
	
	public CourseDataUtil(){ }

	@Autowired
	private CourseHome ch = new CourseHome();
	@Autowired
	private PersonHome ph = new PersonHome();
	
	// Validation
	private static String INVALID_DEPARTMENT_ERROR = "No valid Department selected.";
	private static Integer MIN_CREDIT = 0;
	private static Integer MAX_CREDIT = 5;
	private static String CREDIT_ERROR = "Course Credit value must be between 0.0 and 5.0.";
	private static int MIN_COURSE_NAME_LENGTH = 2;
	private static int MAX_COURSE_NAME_LENGTH = 64;
	private static String MIN_COURSE_NAME_ERROR = "Course Name value must be at least 2 characters long.";
	private static String MAX_COURSE_NAME_ERROR = "Course Name value must be at most 64 characters long.";
	private static int MIN_COURSE_NUMBER_LENGTH = 3;
	private static int MAX_COURSE_NUMBER_LENGTH = 12;
	private static String COURSE_NUMBER_ERROR = "Course Number value must between "+MIN_COURSE_NUMBER_LENGTH+
												" and "+MAX_COURSE_NUMBER_LENGTH+" digits long";
	
	public Course populateCourse(String courseName, String courseNumber, Department dept, Integer credit){
		Course course = new Course();
		course.setCourseNumber(courseNumber);
		course.setCourseName(courseName);
		course.setDepartment(dept);
		course.setCredit(credit);
		return course;
	}
	
	public Course createCourse(Course course) throws InvalidCourseException {
		validateCourse(course);
		// Save Course to DB
		log.info("Creating Course");
		log.debug(describeCourse(course));
		ch.persist(course);
		return course;
	}
	
	@Transactional
	public Course createCourseForTeacher(Integer userId, Course course) throws InvalidCourseException {
		List<String> err = new ArrayList<String>();
		// Save Teaches join to DB
		log.info("Creating Teaches Association");
		Person p = ph.findById(userId);
		if(userId == null){
			err.add("No Valid User ID given");
			throw new InvalidCourseException(err);
		}
		if(p == null){
			err.add("No Valid User for ID["+userId+"]");
			throw new InvalidCourseException(err);
		}
		course.getPersons().add(p);
		Course newCourse = createCourse(course);
		return newCourse;
	}
	
	public String describeCourse(Course course){
		String str = "Course ["+course.getCourseName()+"]:\r\n";
		str += "- Id ["+course.getId()+"]\r\n";
		str += "- Name: ["+course.getCourseName()+"]\r\n";
		str += "- Number: ["+course.getCourseNumber()+"]\r\n";
		String deptName = course.getDepartment() == null ? "" : course.getDepartment().getName();
		str += "- Department: ["+deptName+"]\r\n";
		str += "- Credit: ["+course.getCredit()+"]\r\n";
		return str;
	}
	
	public void deleteCourse(Integer id){
		// TODO: test this
		Course del = ch.findById(id); 
		ch.remove(del);
	}
	
	public Course findCourse(Integer id){
		// TODO: test this
		Course course = ch.findById(id);
		course.getDepartment();
		course.setPersons(course.getPersons());
		return course;
	}
	
	public boolean updateCourse(Course course) throws InvalidCourseException {
		try{
			validateCourse(course);
			Course c = ch.findById(course.getId());
			course.setPersons(c.getPersons());
			ch.merge(course);
			return true;
		}catch(RuntimeException re){
			List<String> err = new ArrayList<String>();
			err.add(re.getMessage());
			throw new InvalidCourseException(err);
		}
	}
	
	public List<Course> getCourses(){
		return ch.getCourses();
		
	}
	
	public List<Course> getCoursesForTeacher(Integer userId){
		List<Course> courses = ch.getCoursesForPersonId(userId);
		return courses;
	}
	
	public List<Map<String,Object>> getDataForAllCourses(){
		List<Course> courses = ch.getCourses();
		return getCoursesData(courses);
	}
	
	public List<Map<String, Object>> getDataForTeacherCourses(Integer userId){
		List<Course> courses = getCoursesForTeacher(userId);
		return getCoursesData(courses);
	}
	
	public List<Map<String,Object>> getCoursesData(List<Course> courses){
		List<Map<String,Object>> coursesData = new ArrayList<Map<String,Object>>();
		for(Course course : courses){
			coursesData.add(mapCourse(course));
		}
		return coursesData;
	}
	
	public Map<String,Object> mapCourse(Course course){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", course.getId());
		map.put("courseNumber", course.getCourseNumber());
		map.put("courseName", course.getCourseName());
		Department dept = course.getDepartment();
		map.put("deptname", dept.getName());
		map.put("deptabbreviation", dept.getAbbreviation());
		map.put("credit", course.getCredit());
		return map;
	}
	
	public List<String> courseErrors(Course course) {
		return getCourseErrors(course);
	}
	
	private List<String> getCourseErrors(Course course){
		List<String> errors = new ArrayList<String>();
		if(course.getDepartment() == null)
			errors.add(INVALID_DEPARTMENT_ERROR);
		if(course.getCourseName() == null || course.getCourseName().length() < MIN_COURSE_NAME_LENGTH)
			errors.add(MIN_COURSE_NAME_ERROR);
		if(course.getCourseName().length() > MAX_COURSE_NAME_LENGTH)
			errors.add(MAX_COURSE_NAME_ERROR);
		if(course.getCourseNumber() == null || course.getCourseNumber().length() < MIN_COURSE_NUMBER_LENGTH || 
				course.getCourseNumber().length() > MAX_COURSE_NUMBER_LENGTH)
			errors.add(COURSE_NUMBER_ERROR);
		if(MIN_CREDIT.compareTo(course.getCredit()) > 0 || MAX_CREDIT.compareTo(course.getCredit()) < 0)
			errors.add(CREDIT_ERROR);
		return errors;
	}
	
	public void validateCourse(Course course) throws InvalidCourseException {
		List<String> errors = getCourseErrors(course);
		if(!errors.isEmpty()){
			throw new InvalidCourseException(errors);
		}
	}
}