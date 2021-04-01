package com.excilys.cdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputerController {
	
	@Autowired
	private ComputerService computerService;

	public ComputerController() {
		super();
	}

	public void create(Computer c) {
		this.computerService.create(c);
	}

	public void delete(int id) {
		this.computerService.delete(id);
	}
	
	public void update(int id, Computer c) {
		this.computerService.update(id, c);
	}

	public List<Computer> find(int id) {
		return this.computerService.find(id);
	}
	
	public List<Computer> find(String name) {
		return this.computerService.find(name);
	}
	
	public List<Computer> findAll() {		
		return this.computerService.findAll();
	}
}
