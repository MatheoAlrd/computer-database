package com.excilys.cdb.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer,Integer>{
	
	public long countByNameLike(String name);
	
	public Computer findById(int id);
	public List<Computer> findByNameLike(String name, Pageable pageable, Sort sort);
	
	public void create(Computer c);
	public void delete(int id);
	public <S> S save(Computer c);
	
}
