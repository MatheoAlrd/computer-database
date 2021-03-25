package com.excilys.cdb.exception;

public class NameInvalidException extends InvalidValuesException{

	private static final long serialVersionUID = 7055369413693795028L;
	
	public NameInvalidException() {
		System.out.println("Name is not valid");
	}

}
