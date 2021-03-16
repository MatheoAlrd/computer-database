package com.excilys.cdb.service;

import com.excilys.cdb.model.dao.ComputerDAO;

public class ComputerService {
	
	private ComputerDAO computerDAO = ComputerDAO.getInstance();

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}

}
