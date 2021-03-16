package com.excilys.cdb.ui;

import java.util.List;
import java.util.Map;

public class CompanyView {
	
	public void showMenuCompany() {
		
		System.out.println("+--------------------------+");
		System.out.println("|       Company Menu       |");
		System.out.println("+--------------------------+");
		System.out.println("| 1.    Find a Company     |");
		System.out.println("+--------------------------+");
		System.out.println("| 2.   List all Companies  |");
		System.out.println("+--------------------------+");
		System.out.println("| 3.         Quit          |");
		System.out.println("+--------------------------+");
	}

	public void printType() {		
		System.out.println("\n-- Company --");
	}

	public void printErrorDoesNotExist() {
		System.out.println("This company does not exist");
	}

	public void print(Map<String,Object> map) {
		
		this.printType();
		map.entrySet().stream().forEach(System.out::println);
	}

	public void printId(int id) {
		System.out.println("Id: " + id);
	}

	public void printName(String name) {
		System.out.println("Name: " + name);
	}

	public void printAll(List<Map<String,Object>> listCompany) {
		for (Map<String,Object> map : listCompany) {
			print(map);
		}		
	}

}
