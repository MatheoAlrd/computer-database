package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.mapper.CompanyMapper;

public class CompanyService {
	
	CompanyMapper companyMapper = new CompanyMapper();
	
	private CompanyDAO companyDAO = CompanyDAO.getInstance();

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public Map<String,Object> find(int id) {
		
		return this.companyMapper.mapFromCompany(this.companyDAO.find(id));
	}

	public List<Map<String, Object>> getAll() {
		
		List<Map<String,Object>> listCompany = new ArrayList<Map<String,Object>>();
		
		for(Company c : this.companyDAO.findAll()) {
			listCompany.add(this.companyMapper.mapFromCompany(c));
		}
		return listCompany;
	}


}
