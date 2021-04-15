package com.excilys.cdb.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateInvalidException extends InvalidValuesException {
	
	protected static Logger logger = LoggerFactory.getLogger(DateInvalidException.class);
	
	private static final long serialVersionUID = 4946354253696542964L;

	public DateInvalidException() {
		logger.error("Date is not valid");
	}

}
