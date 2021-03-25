package com.excilys.cdb.exception;

public class DateIntervalInvalidException extends InvalidValuesException {
	
	private static final long serialVersionUID = -9141601588035365560L;

	public DateIntervalInvalidException() {
		System.out.println("Discontinued is before Intnroduced");
	}

}
