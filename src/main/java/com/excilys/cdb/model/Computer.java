
package com.excilys.cdb.model;

import java.time.LocalDate;

import com.excilys.cdb.model.builder.ComputerBuilder;

public class Computer {

	// ID of the Computer
	private int id = 0;

	// Name of the Computer
	private String name = "";

	// Date when the Computer has been introduced
	private LocalDate introduced = null;

	// Date when the Computer has been discontinued
	private LocalDate discontinued = null;

	// Company of the Computer
	private Company company = null;

	/*
	 * Constructors
	 */

	public Computer() {
		super();
	}

	public Computer(ComputerBuilder computerBuilder) {
		this.id = computerBuilder.getId();
		this.name = computerBuilder.getName();
		this.introduced = computerBuilder.getIntroduced();
		this.discontinued = computerBuilder.getDiscontinued();
		this.company = computerBuilder.getCompany();
	}

	/*
	 * Getter and Setters
	 */

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return this.introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return this.discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	/*
	 * toString - hashCode() - equals()
	 */

	@Override
	public String toString() {
		return "id = " + this.id +
				"\nname = " + this.name +
				"\ndate introduced = " + this.introduced +
				"\ndate discontinued = " + this.discontinued +
				"\ncompany = " + this.company +
				"\n";
	}

	@Override
	public int hashCode() {
		final int prime = 12;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}
		if (discontinued == null) {
			if (other.discontinued != null) {
				return false;
			}
		} else if (!discontinued.equals(other.discontinued)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (introduced == null) {
			if (other.introduced != null) {
				return false;
			}
		} else if (!introduced.equals(other.introduced)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}



}
