package de.fhb.dassystem.login;

public class DataAccessException extends RuntimeException{

	public DataAccessException(){
		super();
	}
	
	public DataAccessException(String message){
		super(message);
	}
	
	public DataAccessException(String message, Exception ex){
		super(message, ex);
	}
}
