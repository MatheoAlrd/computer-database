package com.excilys.cdb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.service.ComputerService;

@Controller
public class ComputerController {
	
	private ComputerService computerService;

	public ComputerController(ComputerService computerService) {
		super();
		this.computerService = computerService;
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
	
	public List<Computer> findPageOrderBy(String name, Page<ComputerDTO> page) {
		return this.computerService.findPageOrderBy(name, page);
	}
	
	public int count() {
		return this.computerService.count();
	}
}
