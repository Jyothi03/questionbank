package org.questionBank.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.questionBank.data.Department;
import org.questionBank.exception.InvalidDepartmentException;
import org.questionBank.home.DepartmentHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDataUtil {

	private static final Logger log = LogManager.getLogger(DepartmentDataUtil.class);
	
	public DepartmentDataUtil(){ }

	@Autowired
	private DepartmentHome dh = new DepartmentHome();

	// Validation
	private static int MIN_NAME_LENGTH = 3;
	private static int MAX_NAME_LENGTH = 50;
	private static String NAME_LENGTH_ERROR = "Department Name value must between "+MIN_NAME_LENGTH+
											  " and "+MAX_NAME_LENGTH+" characters long";
	private static int MIN_ABBREVIATION_LENGTH = 2;
	private static int MAX_ABBREVIATION_LENGTH = 10;
	private static String ABBREVIATION_LENGTH_ERROR = "Department Abbreviation value must between "+MIN_ABBREVIATION_LENGTH+
											  " and "+MAX_ABBREVIATION_LENGTH+" characters long";
	
	public Department populateDepartment(String name, String abbreviation){
		Department dept = new Department();
		dept.setName(name);
		dept.setAbbreviation(abbreviation);
		return dept;
	}
	
	public Department createDepartment(Department dept) throws InvalidDepartmentException {
		validateDepartment(dept);
		// Save Course to DB
		log.info("Creating Course");
		log.debug(describeDepartment(dept));
		dh.persist(dept);
		return dept;
	}
	
	public String describeDepartment(Department dept){
		String str = "Department ["+dept.getName()+"]:\r\n";
		str += "- Id ["+dept.getId()+"]\r\n";
		str += "- Name: ["+dept.getName()+"]\r\n";
		str += "- Abbreviation: ["+dept.getAbbreviation()+"]\r\n";
		return str;
	}
	
	public void deleteDepartment(Integer id){
		// TODO: test this
		Department del = dh.findById(id); 
		dh.remove(del);
	}
	
	public Department findDepartment(Integer id){
		// TODO: test this
		Department dept = dh.findById(id);
		return dept;
	}
	
	public boolean updateDepartment(Department dept) throws InvalidDepartmentException {
		try{
			validateDepartment(dept);
			dh.merge(dept);
			return true;
		}catch(RuntimeException re){
			List<String> err = new ArrayList<String>();
			err.add(re.getMessage());
			throw new InvalidDepartmentException(err);
		}
	}
	
	public List<Department> getDepartments(){
		log.info("Retrieving Departments");
		return dh.getDepartments();
	}
	
	public List<String> departmentErrors(Department department) {
		return getDepartmentErrors(department);
	}
	
	private List<String> getDepartmentErrors(Department department){
		List<String> errors = new ArrayList<String>();
		if(department.getName() == null || department.getName().length() < MIN_NAME_LENGTH || 
				department.getName().length() > MAX_NAME_LENGTH)
			errors.add(NAME_LENGTH_ERROR);
		if(department.getAbbreviation() == null || department.getAbbreviation().length() < MIN_ABBREVIATION_LENGTH || 
				department.getAbbreviation().length() > MAX_ABBREVIATION_LENGTH)
			errors.add(ABBREVIATION_LENGTH_ERROR);
		return errors;
	}
	
	public void validateDepartment(Department department) throws InvalidDepartmentException {
		List<String> errors = getDepartmentErrors(department);
		if(!errors.isEmpty()){
			throw new InvalidDepartmentException(errors);
		}
	}

}
