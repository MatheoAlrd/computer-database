package com.excilys.cdb.controller;

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
	
	public void update(int id, Computer c) {
		this.computerService.getComputerDAO().update(id, c);
	}

	public List<Computer> loadById(int id) {
		return this.computerService.getComputerDAO().find(id);
	}
	
	public List<Computer> loadByName(String name) {
		return this.computerService.getComputerDAO().find(name);
	}
	
	public List<Computer> getAll() {		
		return this.computerService.getComputerDAO().findAll();
	}


}
