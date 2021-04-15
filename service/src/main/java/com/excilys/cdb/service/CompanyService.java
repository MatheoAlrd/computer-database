package com.excilys.cdb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.dao.CompanyRepository;
import com.excilys.cdb.mapper.CompanyMapper;

@Service
public class CompanyService {

	protected static Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	private CompanyRepository companyDAO;

	public CompanyService(CompanyRepository companyDAO) {
		super();
		this.companyDAO = companyDAO;
	}

	public Company find(int id) {		
		return this.companyDAO.findById(id);
	}

	public List<Company> findAll() {		
		return this.companyDAO.findAll();
	}
	
	public void create(Company c){
		companyDAO.create(c);
	}
	
	public void delete(int id) {
		companyDAO.delete(id);
	}


}
