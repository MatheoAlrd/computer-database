package com.excilys.cdb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.dao.ComputerRepository;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;

@Service
public class ComputerService {
	
	protected static Logger logger = LoggerFactory.getLogger(ComputerService.class);
	
	private ComputerRepository computerRepository;
	private ComputerMapper computerMapper;

	
	public ComputerService(ComputerRepository computerRepository, ComputerMapper computerMapper) {
		super();
		this.computerRepository = computerRepository;
		this.computerMapper = computerMapper;
	}
	
	public ComputerDTO create(Computer c) {
		return this.computerMapper.toComputerDTO(this.computerRepository.save(c));
	}
	
	public void delete(int id) {
		this.computerRepository.deleteById(id);
	}
	
	public ComputerDTO update(Computer c) {
		return this.computerMapper.toComputerDTO(this.computerRepository.save(c));
	}

	public ComputerDTO find(int id) {		
		return this.computerMapper.toComputerDTO(this.computerRepository.findById(id));
	}
	
	public List<ComputerDTO> findPageOrderBy(String name, Pageable pageable) {		
		return this.computerRepository.findByNameLike(name, pageable).stream().map(c -> this.computerMapper.toComputerDTO(c)).collect(Collectors.toList());
	}	
	
	public int count() {		
		return this.computerRepository.countByNameLike("");
	}
	
	public int count(String name) {		
		return this.computerRepository.countByNameLike(name);
	}
}
