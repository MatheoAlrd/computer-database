package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.mapper.CompanyMapper;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CompanyService {

	protected static Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private CompanyDAO companyDAO;

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public List<Company> find(int id) {

		List<Company> listCompany = new ArrayList<Company>();
		try {
			for(CompanyDTO c : this.companyDAO.find(id)) {
				listCompany.add(this.companyMapper.toCompany(c).orElseThrow());
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
				listCompany.add(this.companyMapper.toCompany(c).orElseThrow());
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
