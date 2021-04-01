package com.excilys.cdb.model.validator;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.IDInvalidException;
import com.excilys.cdb.exception.InvalidValuesException;
import com.excilys.cdb.exception.NameInvalidException;
import com.excilys.cdb.model.dto.CompanyDTO;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CompanyValidator {
		
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
