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

	public boolean create(Computer c) {
		return this.computerService.getComputerDAO().create(c);
	}

	public boolean delete(int id) {
		return this.computerService.getComputerDAO().delete(id);
	}
	
	public boolean updateName(int id, String name) {
		return this.computerService.getComputerDAO().updateName(id, name);
	}
	
	public boolean updateIntroduced(int id, LocalDate introduced) {
		return this.computerService.getComputerDAO().updateIntroduced(id, introduced);
	}
	
	public boolean updateDiscontinued(int id, LocalDate discontinued) {
		return this.computerService.getComputerDAO().updateDiscontinued(id, discontinued);
	}
	
	public boolean updateCompanyId(int id, int companyId) {
		return this.computerService.getComputerDAO().updateCompany(id, companyId);
	}
	
	public void update(int id, Computer c) {
		this.computerService.getComputerDAO().update(id, c);
	}

	public Computer loadById(int id) {
		return this.computerService.getComputerDAO().find(id);
	}

	public List<Computer> getAll() {		
		return this.computerService.getComputerDAO().getAll();
	}
}
