package com.excilys.cdb.controller;

import java.time.LocalDate;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.dao.ComputerDAO;

public class ComputerController {

	private ComputerDAO computerDAO = ComputerDAO.getInstance();

	public ComputerController() {
		super();
	}

	public boolean create(Computer c) {
		return this.computerDAO.create(c);
	}

	public boolean delete(int id) {
		return this.computerDAO.delete(id);
	}
	
	public boolean updateName(int id, String name) {
		return this.computerDAO.updateName(id, name);
	}
	
	public boolean updateIntroduced(int id, LocalDate introduced) {
		return this.computerDAO.updateIntroduced(id, introduced);
	}
	
	public boolean updateDiscontinued(int id, LocalDate discontinued) {
		return this.computerDAO.updateDiscontinued(id, discontinued);
	}
	
	public boolean updateCompanyId(int id, int companyId) {
		return this.computerDAO.updateCompany(id, companyId);
	}
	
	public void update(int id, Computer c) {
		this.computerDAO.update(id, c);
	}

	public Computer loadById(int id) {
		return this.computerDAO.find(id);
	}

	public List<Computer> getAll() {		
		return this.computerDAO.getAll();
	}
}
