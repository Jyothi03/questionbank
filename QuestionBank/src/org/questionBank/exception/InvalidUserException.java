package org.questionBank.exception; //exception

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class InvalidUserException  extends Exception {
	
	private String message;
	private List<String> errors;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidUserException(List<String> errors){
		message = compileErrorString(errors);
	}

	private String compileErrorString(List<String> errs){
		errors = new ArrayList<String>();
		String errorsStr = "Errors creating Answer:\r\n";
		StringJoiner errJoiner = new StringJoiner("\r\n");
		for(String err : errs){ 
			errors.add(err);
			errJoiner.add("* " + err);
		}
		errorsStr += errJoiner.toString();
		return errorsStr; 
	}
	
	public String getMessage(){
		return message;
	}
	
	public List<String> getErrors(){
		List<String> errs = new ArrayList<String>();
		for(String err : errors){
			errs.add(err);
		}
		return errs;
	}

}
