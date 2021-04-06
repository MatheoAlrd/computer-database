package com.excilys.cdb.ui;

import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;

@Component
public class CompanyView {
	
	public void showMenuCompany() {
		
		System.out.println();
		System.out.println("+--------------------------+");
		System.out.println("|       Company Menu       |");
		System.out.println("+--------------------------+");
		System.out.println("| 1.    Find a Company     |");
		System.out.println("+--------------------------+");
		System.out.println("| 2.   List all Companies  |");
		System.out.println("+--------------------------+");
		System.out.println("| 3.    Create Company     |");
		System.out.println("+--------------------------+");
		System.out.println("| 4.    Delete Company     |");
		System.out.println("+--------------------------+");
		System.out.println("| 5.         Quit          |");
		System.out.println("+--------------------------+");
	}

	public void printType() {		
		System.out.println("\n-- Company --");
	}

	public void printErrorDoesNotExist() {
		System.out.println("This company does not exist");
	}

	public void print(Company c) {		
		if(c == null) {
			this.printErrorDoesNotExist();
		} else {
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

	public void printAll(List<Company> list) {
		for (Company c : list) {
			print(c);
		}		
	}

}
