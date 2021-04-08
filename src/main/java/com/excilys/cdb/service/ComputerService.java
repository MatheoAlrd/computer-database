package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.mapper.ComputerMapper;

@Service
public class ComputerService {
	
	protected static Logger logger = LoggerFactory.getLogger(ComputerService.class);
	
	private ComputerMapper computerMapper;
	private ComputerDAO computerDAO;
	
	public ComputerService(ComputerMapper computerMapper, ComputerDAO computerDAO) {
		super();
		this.computerMapper = computerMapper;
		this.computerDAO = computerDAO;
	}

	public void create(Computer c) {
		this.computerDAO.create(computerMapper.toComputerDTO(c));
	}
	
	public void delete(int id) {
		this.computerDAO.delete(id);
	}
	
	public void update(int id, Computer c) {
		this.computerDAO.update(id, computerMapper.toComputerDTO(c));
	}

	public List<Computer> find(int id) {
		List<Computer> listComputer = new ArrayList<Computer>();

		for(ComputerDTO c : this.computerDAO.find(id)) {
			listComputer.add(this.computerMapper.toComputer(c));
		}
		return listComputer;
	}
	
	public List<Computer> findPageOrderBy(String name, Page<ComputerDTO> page) {
		List<Computer> listComputer = new ArrayList<Computer>();

		for(ComputerDTO c : this.computerDAO.findPageOrderBy(name, page)) {
			listComputer.add(this.computerMapper.toComputer(c));
		}
		return listComputer;
	}	
	
	public int count() {		
		return this.computerDAO.count("");
	}
	
	public int count(String name) {		
		return this.computerDAO.count(name);
	}

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}
}
