package com.excilys.cdb.model.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.builder.CompanyBuilder;
import com.excilys.cdb.model.builder.ComputerBuilder;

public class ComputerValidator {

	public ComputerValidator() {
		super();
	}

	public Computer validateComputer(String name, String introduced, String discontinued, String companyId) {
		return new ComputerBuilder()
				.setName(name)
				.setIntroduced(introduced.isEmpty() ? 
						null : LocalDate.parse(introduced, DateTimeFormatter.ISO_LOCAL_DATE))
				.setDiscontinued(discontinued.isEmpty() ? 
						null : LocalDate.parse(discontinued, DateTimeFormatter.ISO_LOCAL_DATE))
				.setCompany(companyId.isEmpty() ?
						null : new CompanyBuilder().setId(Integer.parseInt(companyId)).build())
				.build();
	}

}
