package com.excilys.cdb.dto;

public class CompanyDTO {
	
	private String id;
	private String name;
	
	public CompanyDTO() { }

	public CompanyDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}
}
