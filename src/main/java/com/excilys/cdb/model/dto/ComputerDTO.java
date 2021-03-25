package com.excilys.cdb.model.dto;

public class ComputerDTO {
	
	private String name;
	private String introduced;
	private String discontinued;
	private String companyID;
	
	public ComputerDTO() { }

	public ComputerDTO(String name, String introduced, String discontinued, String companyID) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyID = companyID;
	}

	public String getName() {
		return name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public String getCompanyID() {
		return companyID;
	};
	
	

}
