package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.mapper.CompanyMapper;

public class CompanyService {

	private CompanyMapper companyMapper = new CompanyMapper();
	private CompanyDAO companyDAO = CompanyDAO.getInstance();

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public List<Company> find(int id) {

		List<Company> listCompany = new ArrayList<Company>();

		for(CompanyDTO c : this.companyDAO.find(id)) {
			listCompany.add(this.companyMapper.toCompany(c).orElseThrow());
		}
		return listCompany;
	}

	public List<Company> findAll() {

		List<Company> listCompany = new ArrayList<Company>();

		for(CompanyDTO c : this.companyDAO.findAll()) {
			listCompany.add(this.companyMapper.toCompany(c).orElseThrow());
		}
		return listCompany;
	}


}
