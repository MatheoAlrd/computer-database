package com.excilys.cdb.controller;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.service.CompanyService;

public class CompanyController {
	
	private CompanyService companyService = new CompanyService();
	

	public CompanyController() {
	}
	
	public Company loadById(int id) {
		
		try {
			return this.companyService.getCompanyDAO().find(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public List<Company> getAll() {
		
		return this.companyService.getCompanyDAO().getAll();
	}
}
