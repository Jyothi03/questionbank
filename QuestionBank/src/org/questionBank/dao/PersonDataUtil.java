package org.questionBank.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.questionBank.data.Person;
import org.questionBank.exception.InvalidCredentialException;
import org.questionBank.exception.InvalidUserException;
import org.questionBank.exception.UserAlreadyExistException;
import org.questionBank.home.PersonHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDataUtil {

	private static final Logger log = LogManager.getLogger(PersonDataUtil.class);

	private static int MIN_USER_NAME_LENGTH = 2;
	private static int MAX_USER_NAME_LENGTH = 20;
	private static String INVALID_USER_NAME_ERROR = "User Name must be between "+MIN_USER_NAME_LENGTH+" and "+MAX_USER_NAME_LENGTH+" characters long.";
	private static int MIN_FIRST_NAME_LENGTH = 2;
	private static int MAX_FIRST_NAME_LENGTH = 50;
	private static String INVALID_FIRST_NAME_ERROR = "User First Name must be between "+MIN_FIRST_NAME_LENGTH+" and "+MAX_FIRST_NAME_LENGTH+" characters long.";
	private static int MIN_LAST_NAME_LENGTH = 2;
	private static int MAX_LAST_NAME_LENGTH = 50;
	private static String INVALID_LAST_NAME_ERROR = "User Last Name must be between "+MIN_LAST_NAME_LENGTH+" and "+MAX_LAST_NAME_LENGTH+" characters long.";
	private static int MIN_PASSWORD_LENGTH = 2;
	private static int MAX_PASSWORD_LENGTH = 50;
	private static String INVALID_PASSWORD_ERROR = "Password must be between "+MIN_PASSWORD_LENGTH+" and "+MAX_PASSWORD_LENGTH+" characters long.";
	
	public PersonDataUtil(){ }
	
	@Autowired
	private PersonHome ph;
	
	
	public boolean adminLogin(String username,String password) throws InvalidCredentialException{
		validateUserCredential(username,password);
		if(username.equals("admin")&&password.equals("admin")){
			return true;
		}else{
			throw new InvalidCredentialException("Invalid Username/Password");
		}
	}
	
	public Person teacherLogin(String userName,String password) throws InvalidCredentialException {
		validateUserCredential(userName,password);
		Person person = findUserByUserName(userName);
		
		if(person.getPassword().equals(password)){
			return person;
		}else{
			throw new InvalidCredentialException("Invalid Password");
		}
	}
	
	public Person findPerson(Integer id){
		// TODO: test this
		Person person = ph.findById(id);
		return person;
	}
	
	public Person findUserByUserName(String userName) throws InvalidCredentialException {
		List<Person> people = ph.findUsersByUserName(userName);
		if(people==null || people.isEmpty()){
			throw new InvalidCredentialException("Invalid Username");
		}
		if(people.size() > 1){
			throw new InvalidCredentialException("Multiple Users Found");
		}
		Person person  = people.get(0);
		return person;
	}
	
	private void validateUserCredential(String username, String password)  throws InvalidCredentialException{
		if(username==null || username.isEmpty() || username.length() < MIN_USER_NAME_LENGTH || username.length() > MAX_USER_NAME_LENGTH)
			throw new InvalidCredentialException(INVALID_USER_NAME_ERROR);
		if(password==null || password.isEmpty() || password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH)
			throw new InvalidCredentialException(INVALID_PASSWORD_ERROR);
	}
	private void validateUserCredential(String username, String password, String rpassword)  throws InvalidCredentialException{
		if(username==null || username.isEmpty() || username.length() < MIN_USER_NAME_LENGTH || username.length() > MAX_USER_NAME_LENGTH)
			throw new InvalidCredentialException(INVALID_USER_NAME_ERROR);
		if(password==null || password.isEmpty() || password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH)
			throw new InvalidCredentialException(INVALID_PASSWORD_ERROR);
		if(rpassword==null || rpassword.isEmpty())
			throw new InvalidCredentialException("Repeat Password Empty");
		if(!password.equals(rpassword))
			throw new InvalidCredentialException("Password Not Matching");
	}
	
	public Person createUser(String userName, String password, String firstName, String rpassword, 
			String lastName, boolean admin) throws InvalidCredentialException, UserAlreadyExistException {
		Person person=null;
		validateUserCredential(userName, password , rpassword);
		List<Person> people=ph.findUsersByUserName(userName);
		if(people != null && !people.isEmpty())
			throw new UserAlreadyExistException("User name not available");
		person=new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPassword(password);
		person.setUserName(userName);
		person.setAdmin(admin);
		// Save Course to DB
		log.info("Creating person");
		ph.persist(person);
		return person;
	}
	
	public Person createPerson( String userName, String password, String firstName, String rpassword, String lastName) throws InvalidCredentialException, UserAlreadyExistException {
		Person person=null;
		validateUserCredential(userName, password , rpassword);
		List<Person> people=ph.findUsersByUserName(userName);
		if(people != null && !people.isEmpty())
			throw new UserAlreadyExistException("User name not available");
		person=new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPassword(password);
		person.setUserName(userName);
		// Save Course to DB
		log.info("Creating person");
		ph.persist(person);
		return person;
	}
	
	public Person resetUserPassword(Integer id, String password, String rpassword) throws InvalidCredentialException, InvalidUserException {
		try{			
			Person u = ph.findById(id);
			validateUserCredential(u.getUserName(), password , rpassword);
			u.setPassword(password);
			u.setCourses(u.getCourses());
			Person updatedPerson = ph.merge(u);
			return updatedPerson;
		}catch(RuntimeException re){
			List<String> err = new ArrayList<String>();
			err.add(re.getMessage());
			throw new InvalidUserException(err);
		}
	}

	public boolean updateUser(Person user) throws InvalidUserException {
		try{			
			Person u = ph.findById(user.getId());
			user.setPassword(u.getPassword());
			validateUser(user);
			user.setCourses(u.getCourses());
			ph.merge(user);
			return true;
		}catch(RuntimeException re){
			List<String> err = new ArrayList<String>();
			err.add(re.getMessage());
			throw new InvalidUserException(err);
		}
	}

	
	public List<String> userErrors(Person user) {
		return getUserErrors(user);
	}
	
	private List<String> getUserErrors(Person user){
		List<String> errors = new ArrayList<String>();
		if(user.getUserName() == null || user.getUserName().length() < MIN_USER_NAME_LENGTH || user.getUserName().length() > MAX_USER_NAME_LENGTH)
			errors.add(INVALID_USER_NAME_ERROR);
		if(user.getFirstName() == null || user.getFirstName().length() < MIN_FIRST_NAME_LENGTH || user.getFirstName().length() > MAX_FIRST_NAME_LENGTH)
			errors.add(INVALID_FIRST_NAME_ERROR);
		if(user.getLastName() == null || user.getLastName().length() < MIN_LAST_NAME_LENGTH || user.getLastName().length() > MAX_LAST_NAME_LENGTH)
			errors.add(INVALID_LAST_NAME_ERROR);
		if(user.getPassword() == null || user.getPassword().length() < MIN_PASSWORD_LENGTH || user.getPassword().length() > MAX_PASSWORD_LENGTH)
			errors.add(INVALID_PASSWORD_ERROR);
		return errors;
	}
	
	public void validateUser(Person user) throws InvalidUserException {
		List<String> errors = getUserErrors(user);
		if(!errors.isEmpty()){
			throw new InvalidUserException(errors);
		}
	}
	
	public List<Person> getAllUsers(){
		return ph.getUsers();
	}
}
