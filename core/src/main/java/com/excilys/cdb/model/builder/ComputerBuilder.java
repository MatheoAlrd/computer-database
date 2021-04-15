package com.excilys.cdb.model.builder;

import java.time.LocalDate;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerBuilder {
	
	// ID of the Computer
	private int id = 0;
	
	// Name of the Computer
	private String name = "";

	// Date when the Computer has been introduced
	private LocalDate introduced = null;

	// Date when the Computer has been discontinued
	private LocalDate discontinued = null;

	// Company ID of the Computer
	private Company company = null;
	
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public LocalDate getIntroduced() {
		return this.introduced;
	}

	public LocalDate getDiscontinued() {
		return this.discontinued;
	}

	public Company getCompany() {
		return this.company;
	}

	public ComputerBuilder setId(int id) {
		this.id = id;
		return this;
	}

	public ComputerBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public ComputerBuilder setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
		return this;
	}

	public ComputerBuilder setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
		return this;
	}

	public ComputerBuilder setCompany(Company company) {
		this.company = company;
		return this;
	}
	
	public Computer build() {
		return new Computer(this);
	}
	
}
