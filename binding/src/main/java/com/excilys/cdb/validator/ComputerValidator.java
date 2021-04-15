package com.excilys.cdb.validator;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.IDInvalidException;
import com.excilys.cdb.exception.DateIntervalInvalidException;
import com.excilys.cdb.exception.DateInvalidException;
import com.excilys.cdb.exception.InvalidValuesException;
import com.excilys.cdb.exception.NameInvalidException;
import com.excilys.cdb.dto.ComputerDTO;

@Component
public class ComputerValidator {
	
	public ComputerValidator() {
		super();
	}
	
	public void validate(ComputerDTO c) throws InvalidValuesException{

		this.isNameValid(c.getName());
		this.isDateValid(c.getIntroduced());
		this.isDateValid(c.getDiscontinued());
		this.isDateIntervalValid(c.getIntroduced(), c.getDiscontinued());
		this.isIdValid(c.getCompanyId());

	}

	private void isIdValid(String id) throws IDInvalidException {
		if(id == null) {
			return;
		}
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
		if(date == null || date == "") {
			return;
		}
		try {
			LocalDate.parse(date);
		}
		catch (Exception e) {
			throw new DateInvalidException();
		}

	}

	private void isDateIntervalValid(String introduced, String discontinued) throws DateIntervalInvalidException {
		if(introduced == null || discontinued == null) {
			return;
		}
		if(introduced == "" || discontinued == "") {
			return;
		}
		if(LocalDate.parse(discontinued).isBefore(LocalDate.parse(introduced))) {
			throw new DateIntervalInvalidException();
		}
	}
}
