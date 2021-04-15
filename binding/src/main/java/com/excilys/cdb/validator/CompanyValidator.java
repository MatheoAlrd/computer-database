package com.excilys.cdb.validator;

import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.IDInvalidException;
import com.excilys.cdb.exception.InvalidValuesException;
import com.excilys.cdb.exception.NameInvalidException;
import com.excilys.cdb.dto.CompanyDTO;

@Component
public class CompanyValidator {
	
	public CompanyValidator() {
		super();
	}
	
	public void validate(CompanyDTO c) throws InvalidValuesException {
		
		this.isIDValid(c.getID());
		this.isNameValid(c.getName());		
	}
	
	private void isIDValid(String id) throws IDInvalidException {
		try {
			Integer.parseInt(id);
		}
		catch (Exception e) {
			throw new IDInvalidException();
		}
	}
	
	private void isNameValid(String name) throws NameInvalidException {
		
		if(name.isBlank() || name.length() > 255) {
			throw new NameInvalidException();
		}
	}
}
