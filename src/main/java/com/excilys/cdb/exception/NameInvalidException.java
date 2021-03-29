package com.excilys.cdb.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NameInvalidException extends InvalidValuesException{
	
	protected static Logger logger = LoggerFactory.getLogger(NameInvalidException.class);

	private static final long serialVersionUID = 7055369413693795028L;
	
	public NameInvalidException() {
		logger.error("Name is not valid");
	}

}
