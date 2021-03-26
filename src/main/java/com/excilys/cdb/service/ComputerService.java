package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.validator.ComputerValidator;

public class ComputerService {
	
	private ComputerDAO computerDAO = ComputerDAO.getInstance();
	
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
	
	public List<Computer> findPage(String name, int pageSize, int offset) {		
		return this.computerDAO.findPage(name, pageSize, offset);
	}
	
	public List<Computer> findAllPage(int pageSize, int offset) {	
		return this.computerDAO.findAllPage(pageSize, offset);
	}
	
	public int count() {		
		return this.computerDAO.count();
	}
	
	public int count(String name) {		
		return this.computerDAO.count(name);
	}

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}
}
