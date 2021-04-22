package com.excilys.cdb.exception;

public class UserAlreadyExistException extends Throwable {

	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistException(String message) {
		System.out.println(message);
	}

}
