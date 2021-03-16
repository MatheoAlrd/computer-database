package com.excilys.cdb.service;

import com.excilys.cdb.model.dao.CompanyDAO;

public class CompanyService {
	
	private CompanyDAO companyDAO = CompanyDAO.getInstance();

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}


}
