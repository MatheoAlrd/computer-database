package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.mapper.CompanyMapper;

@Service
public class CompanyService {

	protected static Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	private CompanyMapper companyMapper;
	private CompanyDAO companyDAO;

	public CompanyService(CompanyMapper companyMapper, CompanyDAO companyDAO) {
		super();
		this.companyMapper = companyMapper;
		this.companyDAO = companyDAO;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public List<Company> find(int id) {

		List<Company> listCompany = new ArrayList<Company>();
		try {
			for(CompanyDTO c : this.companyDAO.find(id)) {
				listCompany.add(this.companyMapper.toCompany(c));
			}
		} catch (NoSuchElementException e) {
			logger.warn("Didn't find the company by its id"+e.getMessage());
		}
		return listCompany;
	}

	public List<Company> findAll() {
		List<Company> listCompany = new ArrayList<Company>();
		try {
			for(CompanyDTO c : this.companyDAO.findAll()) {
				listCompany.add(this.companyMapper.toCompany(c));
			}

		} catch (NoSuchElementException e) {
			logger.warn("Didn't find the company from all of them "+e.getMessage());
		}
		return listCompany;
	}
	
	public void create(CompanyDTO c){
		companyDAO.create(c);
	}
	
	public void delete(int id) {
		companyDAO.delete(id);
	}


}
