package com.excilys.cdb.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.mapper.ComputerMapper;

@Component
public class ComputerRowMapper implements RowMapper<ComputerDTO> {
	
	private ComputerMapper computerMapper;

	@Override
	public ComputerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return this.computerMapper.computerFromResultSet(rs).orElseThrow();
	}
	
	

}
