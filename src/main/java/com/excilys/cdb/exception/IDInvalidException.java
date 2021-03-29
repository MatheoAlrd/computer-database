package com.excilys.cdb.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IDInvalidException extends InvalidValuesException {
	
	protected static Logger logger = LoggerFactory.getLogger(IDInvalidException.class);
	
	private static final long serialVersionUID = -6069173476575182889L;

	public IDInvalidException() {
		logger.error("Company ID is not valid");
	}
}
