package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.mapper.ComputerMapper;

public class ComputerService {
	
	protected static Logger logger = LoggerFactory.getLogger(ComputerService.class);
	
	private ComputerMapper computerMapper = new ComputerMapper();
	private ComputerDAO computerDAO = ComputerDAO.getInstance();
	
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
			listComputer.add(this.computerMapper.toComputer(c).orElseThrow());
		}
		return listComputer;
	}
	
	public List<Computer> find(String name) {
		List<Computer> listComputer = new ArrayList<Computer>();

		for(ComputerDTO c : this.computerDAO.find(name)) {
			listComputer.add(this.computerMapper.toComputer(c).orElseThrow());
		}
		return listComputer;
	}
	
	public List<Computer> findAll() {		
		List<Computer> listComputer = new ArrayList<Computer>();

		for(ComputerDTO c : this.computerDAO.findAll()) {
			listComputer.add(this.computerMapper.toComputer(c).orElseThrow());
		}
		return listComputer;
	}
	
	public List<Computer> findPage(String name, int pageSize, int offset) {
		List<Computer> listComputer = new ArrayList<Computer>();

		for(ComputerDTO c : this.computerDAO.findPage(name, pageSize, offset)) {
			listComputer.add(this.computerMapper.toComputer(c).orElseThrow());
		}
		return listComputer;
	}
	
	public List<Computer> findAllPage(int pageSize, int offset) {
		List<Computer> listComputer = new ArrayList<Computer>();

		for(ComputerDTO c : this.computerDAO.findAllPage(pageSize, offset)) {
			listComputer.add(this.computerMapper.toComputer(c).orElseThrow());
		}
		return listComputer;
	}
	
	public List<Computer> findPageOrderBy(String name, int pageSize, int offset, String sort, boolean asc) {
		List<Computer> listComputer = new ArrayList<Computer>();

		for(ComputerDTO c : this.computerDAO.findPageOrderBy(name, pageSize, offset, sort, asc)) {
			listComputer.add(this.computerMapper.toComputer(c).orElseThrow());
		}
		return listComputer;
	}
	
	public List<Computer> findAllPageOrderBy(int pageSize, int offset, String sort, boolean asc) {
		List<Computer> listComputer = new ArrayList<Computer>();

		for(ComputerDTO c : this.computerDAO.findAllPageOrderBy(pageSize, offset, sort, asc)) {
			listComputer.add(this.computerMapper.toComputer(c).orElseThrow());
		}
		return listComputer;
	}
	
	
	public int count() {		
		return this.computerDAO.count();
	}
	
	public int count(String name) {		
		return this.computerDAO.count(name);
	}

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}
}
