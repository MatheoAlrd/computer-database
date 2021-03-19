package com.excilys.cdb.controller;

import java.time.LocalDate;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

public class ComputerController {

	private ComputerService computerService = new ComputerService();

	public ComputerController() {
		super();
	}

	public void create(Computer c) {
		this.computerService.getComputerDAO().create(c);
	}

	public void delete(int id) {
		this.computerService.getComputerDAO().delete(id);
	}
	
	public void updateName(int id, String name) {
		this.computerService.getComputerDAO().updateName(id, name);
	}
	
	public void updateIntroduced(int id, LocalDate introduced) {
		this.computerService.getComputerDAO().updateIntroduced(id, introduced);
	}
	
	public void updateDiscontinued(int id, LocalDate discontinued) {
		this.computerService.getComputerDAO().updateDiscontinued(id, discontinued);
	}
	
	public void updateCompanyId(int id, int companyId) {
		this.computerService.getComputerDAO().updateCompany(id, companyId);
	}
	
	public void update(int id, Computer c) {
		this.computerService.getComputerDAO().update(id, c);
	}

	public Computer loadById(int id) {
		return this.computerService.getComputerDAO().find(id);
	}
	
	public List<Computer> loadByName(String name) {
		return this.computerService.getComputerDAO().find(name);
	}
	
	public List<Computer> getAll() {		
		return this.computerService.getComputerDAO().getAll();
	}


}
