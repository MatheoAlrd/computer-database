package com.excilys.cdb.controller;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

public class CompanyController {

	private CompanyService companyService = new CompanyService();


	public CompanyController() {
	}

	public List<Company> find(int id) {

		return this.companyService.find(id);

	}

	public List<Company> findAll() {

		return this.companyService.findAll();
	}
}
