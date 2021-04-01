package com.excilys.cdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.service.CompanyService;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;

	public CompanyController() {
	}

	public List<Company> find(int id) {
		return this.companyService.find(id);
	}

	public List<Company> findAll() {
		return this.companyService.findAll();
	}
	
	public void create(CompanyDTO c) {
		this.companyService.create(c);
	}
	
	public void delete(int id) {
		this.companyService.delete(id);
	}
}
