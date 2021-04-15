package com.excilys.cdb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Integer>{
	
	public Company findById(int id);
	public List<Company> findAll();
	
	public void create(Company c);
	public void delete(int id);
}
