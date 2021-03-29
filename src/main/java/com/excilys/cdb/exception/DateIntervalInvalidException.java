package com.excilys.cdb.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateIntervalInvalidException extends InvalidValuesException {
	
	protected static Logger logger = LoggerFactory.getLogger(DateIntervalInvalidException.class);
	
	private static final long serialVersionUID = -9141601588035365560L;

	public DateIntervalInvalidException() {
		logger.error("Discontinued is before Introduced");
	}

}
