package com.excilys.cdb.model.dto;

public class ComputerDTO {
	
	private String id = "";
	private String name = "";
	private String introduced = "";
	private String discontinued = "";
	private String companyID = "";
	private String companyName = "";
	
	public ComputerDTO() { }

	public ComputerDTO(String id, String name, String introduced, String discontinued, String companyID, String companyName) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyID = companyID;
		this.companyName = companyName;
	}
	
	public String getId() {
		return id;
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

	public String getCompanyId() {
		return companyID;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", companyID=" + companyID + ", companyName=" + companyName + "]";
	}
	

}
