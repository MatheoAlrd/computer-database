package com.excilys.cdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	public User findByFirstName(String firstName);
	public User findByLastName(String lastName);

}
