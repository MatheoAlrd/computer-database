package com.excilys.cdb.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer,Integer>{
	
	public int countByNameLike(String name);
	
	public Computer findById(int id);
	public List<Computer> findByNameLike(String name, Pageable pageable);
	
	public void deleteById(int id);
	
}
