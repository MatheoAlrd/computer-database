package com.excilys.cdb.model.validator;

import java.time.LocalDate;

import com.excilys.cdb.exception.IDInvalidException;
import com.excilys.cdb.exception.DateIntervalInvalidException;
import com.excilys.cdb.exception.DateInvalidException;
import com.excilys.cdb.exception.InvalidValuesException;
import com.excilys.cdb.exception.NameInvalidException;
import com.excilys.cdb.model.dto.ComputerDTO;

public class ComputerValidator {

	private static ComputerValidator instance;

	public void validate(ComputerDTO c) throws InvalidValuesException{

		this.isNameValid(c.getName());
		this.isDateValid(c.getIntroduced());
		this.isDateValid(c.getDiscontinued());
		this.isValidInterval(c.getIntroduced(), c.getDiscontinued());
		this.isIDValid(c.getCompanyID());

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

	private void isDateValid(String date) throws DateInvalidException {	
		if(date == null) {
			return;
		}
		try {
			LocalDate.parse(date);
		}
		catch (Exception e) {
			throw new DateInvalidException();
		}

	}

	private void isValidInterval(String introduced, String discontinued) throws DateIntervalInvalidException {
		if(introduced == null || discontinued == null) {
			return;
		}
		if(LocalDate.parse(discontinued).isBefore(LocalDate.parse(introduced))) {
			throw new DateIntervalInvalidException();
		}
	}

	public static ComputerValidator getInstance() {
		if(instance == null) {
			instance = new ComputerValidator();
		}
		return instance;
	}


}
