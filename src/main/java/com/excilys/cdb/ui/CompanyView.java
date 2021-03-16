package com.excilys.cdb.ui;

import java.util.List;

import com.excilys.cdb.model.Company;

public class CompanyView {

	public void printType() {		
		System.out.println("-- Company --");
	}

	public void printErrorDoesNotExist() {
		System.out.println("This company does not exist");
	}

	public void print(int id, String name) {
		if (id == 0) {
			this.printErrorDoesNotExist();
		} else {
			this.printType();
			this.printId(id);
			this.printName(name);
		}
	}

	public void print(Company c) {
		if (c != null) {		
			this.printType();
			this.printId(c.getId());
			this.printName(c.getName());
		}
	}

	public void printId(int id) {
		System.out.println("Id: " + id);
	}

	public void printName(String name) {
		System.out.println("Name: " + name);
	}

	public void printAll(List<Company> listCompany) {
		for (Company c : listCompany) {
			print(c);
		}		
	}

}
