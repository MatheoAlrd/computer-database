package com.excilys.cdb.model.validator;

import com.excilys.cdb.exception.IDInvalidException;
import com.excilys.cdb.exception.InvalidValuesException;
import com.excilys.cdb.exception.NameInvalidException;
import com.excilys.cdb.model.dto.CompanyDTO;

public class CompanyValidator {
	
	private static CompanyValidator instance;
	
	public void validateCompany(CompanyDTO c) throws InvalidValuesException {
		
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

	public static CompanyValidator getInstance() {
		if (instance == null) {
			instance = new CompanyValidator();
		}		
		return instance;
	}

}
