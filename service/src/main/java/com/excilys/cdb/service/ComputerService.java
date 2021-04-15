package com.excilys.cdb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.dao.ComputerRepository;

@Service
public class ComputerService {
	
	protected static Logger logger = LoggerFactory.getLogger(ComputerService.class);
	
	private ComputerRepository computerDAO;
	
	public ComputerService(ComputerRepository computerDAO) {
		super();
		this.computerDAO = computerDAO;
	}

	public void create(Computer c) {
		this.computerDAO.create(c);
	}
	
	public void delete(int id) {
		this.computerDAO.delete(id);
	}
	
	public void update(int i, Computer c) {
		//this.computerDAO.save(c);
	}

	public Computer find(int id) {
		
		return this.computerDAO.findById(id);
	}
	
	public List<Computer> findPageOrderBy(String name, Pageable pageable, Sort sort) {
		
		return this.computerDAO.findByNameLike(name, pageable, sort);
	}	
	
	public long count() {		
		return this.computerDAO.countByNameLike("");
	}
	
	public long count(String name) {		
		return this.computerDAO.countByNameLike(name);
	}
}
