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
	
	public Computer validateComputer(Computer c) {
		return new ComputerBuilder()
				.setName(c.getName())
				.setIntroduced(c.getIntroduced().isEmpty() ? 
						null : c.getIntroduced().get())
				.setDiscontinued(c.getDiscontinued().isEmpty() ? 
						null : c.getDiscontinued().get())
				.setCompany(c.getCompany().isEmpty() ?
						null : c.getCompany().get().getId() == 0 ?
								null : c.getCompany().get())
				.build();
	}

	public Computer validateComputer(String name, String introduced, String discontinued, String companyId) {
		return new ComputerBuilder()
				.setName(name)
				.setIntroduced(introduced.isEmpty() ? 
						null : LocalDate.parse(introduced, DateTimeFormatter.ISO_LOCAL_DATE))
				.setDiscontinued(discontinued.isEmpty() ? 
						null : LocalDate.parse(discontinued, DateTimeFormatter.ISO_LOCAL_DATE))
				.setCompany(companyId.isEmpty() ?
						null : companyId == "0" ?
								null : new CompanyBuilder().setId(Integer.parseInt(companyId)).build())
				.build();
	}
	
	public Computer validateComputer(String name, String introduced, String discontinued, String companyId, String companyName) {
		return new ComputerBuilder()
				.setName(name)
				.setIntroduced(introduced.isEmpty() ? 
						null : LocalDate.parse(introduced, DateTimeFormatter.ISO_LOCAL_DATE))
				.setDiscontinued(discontinued.isEmpty() ? 
						null : LocalDate.parse(discontinued, DateTimeFormatter.ISO_LOCAL_DATE))
				.setCompany(companyId.isEmpty() ?
						null : companyId == "0" ?
								null : new CompanyBuilder().setId(Integer.parseInt(companyId))
								.setName(companyName)
								.build())
				.build();
	}

}
