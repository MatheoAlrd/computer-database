package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.util.List;

import com.excilys.cdb.model.Computer;

public class ComputerView {
	
	public void showMenuComputer() {
		
		System.out.println();
		System.out.println("+--------------------------+");
		System.out.println("|       Computer Menu      |");
		System.out.println("+--------------------------+");
		System.out.println("| 1.   Create a Computer   |");
		System.out.println("+--------------------------+");
		System.out.println("| 2.   Delete a Computer   |");
		System.out.println("+--------------------------+");
		System.out.println("| 3.   Update a Computer   |");
		System.out.println("+--------------------------+");
		System.out.println("| 4.    Find a Computer    |");
		System.out.println("+--------------------------+");
		System.out.println("| 5.   List all Computers  |");
		System.out.println("+--------------------------+");
		System.out.println("| 6.         Quit          |");
		System.out.println("+--------------------------+");
	}

	public void printType() {		
		System.out.println("-- Computer --");
	}

	public void printErrorDoesNotExist() {
		System.out.println("This computer does not exist");
	}

	public void print(int id, String name, LocalDate introduced, LocalDate discontinued, int companyId) {
		if (id == 0) {
			this.printErrorDoesNotExist();
		} else {
			this.printType();
			this.printId(id);
			this.printName(name);
			this.printIntroduced(introduced);
			this.printDiscontinued(discontinued);
			this.printCompanyId(companyId);
		}
	}

	public void print(Computer c) {
		if (c != null) {
			this.printType();
			this.printId(c.getId());
			this.printName(c.getName());
			this.printIntroduced(c.getIntroduced().orElse(null));
			this.printDiscontinued(c.getDiscontinued().orElse(null));
			if (c.getCompany().orElse(null) == null) {
				this.printCompanyNull();
			} else {
				this.printCompanyId(c.getCompany().orElse(null).getId());
			}
		}
	}

	public void printId(int id) {
		System.out.println("Id: " + id);
	}

	public void printName(String name) {
		System.out.println("Name: " + name);
	}

	public void printIntroduced(LocalDate introduced) {
		System.out.println("Introduced Date: " + introduced);
	}

	public void printDiscontinued(LocalDate discontinued) {
		System.out.println("Discontinued Date: " + discontinued);
	}

	public void printCompanyId(int companyId) {
		System.out.println("Company id: " + companyId);
	}

	public void printCompanyNull() {
		System.out.println("Company id: null");
	}

	public void printAll(List<Computer> listComputer) {
		for (Computer c : listComputer) {
			print(c);
		}		
	}

}
