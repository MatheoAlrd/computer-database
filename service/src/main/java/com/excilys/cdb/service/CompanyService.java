package com.excilys.cdb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.dao.CompanyRepository;
import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.mapper.CompanyMapper;

@Service
public class CompanyService {

	protected static Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	private CompanyRepository companyRepository;
	private CompanyMapper companyMapper;

	public CompanyService(CompanyRepository companyDAO, CompanyMapper companyMapper) {
		super();
		this.companyRepository = companyDAO;
		this.companyMapper = companyMapper;
	}

	public List<CompanyDTO> findAll() {		
		return this.companyRepository.findAll().stream().map(c -> this.companyMapper.toCompanyDTO(c)).collect(Collectors.toList());
	}
	
	public void create(Company c){
		companyRepository.save(c);
	}
	
	@Transactional
	public void delete(int id) {
		companyRepository.deleteById(id);
	}


}
