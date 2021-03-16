package com.excilys.cdb.controller;

import java.util.List;
import java.util.Map;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

public class CompanyController {

	private CompanyService companyService = new CompanyService();


	public CompanyController() {
	}

	public Company loadById(int id) {

		return this.companyService.find(id);

	}

	public List<Company> getAll() {

		return this.companyService.getAll();
	}
}
