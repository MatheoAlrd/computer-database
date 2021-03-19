package com.excilys.cdb.controller;

import java.util.List;
import java.util.Map;

import com.excilys.cdb.service.CompanyService;

public class CompanyController {

	private CompanyService companyService = new CompanyService();


	public CompanyController() {
	}

	public List<Map<String, Object>> find(int id) {

		return this.companyService.find(id);

	}

	public List<Map<String,Object>> findAll() {

		return this.companyService.findAll();
	}
}
