package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.validator.ComputerValidator;

public class ComputerService {
	
	private ComputerDAO computerDAO = ComputerDAO.getInstance();
	private ComputerValidator computerValidator = new ComputerValidator();
	
	public void create(Computer c) {
		this.computerDAO.create(c);
	}
	
	public void delete(int id) {
		this.computerDAO.delete(id);
	}
	
	public void update(int id, Computer c) {
		this.computerDAO.update(id, c);
	}

	public List<Computer> find(int id) {
		return this.computerDAO.find(id);
	}
	
	public List<Computer> find(String name) {
		return this.computerDAO.find(name);
	}
	
	public List<Computer> findAll() {		
		return this.computerDAO.findAll();
	}
	

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}
	
	public Computer validateComputer(String name, String introduced, String discontinued, String companyId) {
		return this.computerValidator.validateComputer(name, introduced, discontinued, companyId);
	}
	
	public Computer valdiateComputer(Computer c) {
		return this.computerValidator.validateComputer(c);
	}

}
