package com.excilys.cdb.exception;

public class IDInvalidException extends InvalidValuesException {
	
	private static final long serialVersionUID = -6069173476575182889L;

	public IDInvalidException() {
		System.out.println("Company ID is not valid");
	}
}
