package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.mapper.CompanyMapper;

public class CompanyService {
	
	CompanyMapper companyMapper = new CompanyMapper();
	
	private CompanyDAO companyDAO = CompanyDAO.getInstance();

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public Company find(int id) {
		
		return this.companyDAO.find(id);
	}

	public List<Company> getAll() {
		return this.companyDAO.getAll();
	}


}
