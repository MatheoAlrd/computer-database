package com.excilys.cdb.model.builder;

import com.excilys.cdb.model.Company;

public class CompanyBuilder {

	// ID of the Company
	private int id = 0;

	// Name of the Company
	private String name = "";
	
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public CompanyBuilder setId(int id) {
		this.id = id;
		return this;
	}

	public CompanyBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public Company build() {
		return new Company(this);
	}


}
