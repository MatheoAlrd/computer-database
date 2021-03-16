package com.excilys.cdb.controller;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.dao.CompanyDAO;

public class CompanyController {
	
	private CompanyDAO companyDAO = CompanyDAO.getInstance();
	

	public CompanyController() {
	}
	
	public Company loadById(int id) {
		
		try {
			return this.companyDAO.find(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public List<Company> getAll() {
		
		return this.companyDAO.getAll();
	}
}
