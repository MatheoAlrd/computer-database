package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.ComputerDTO;

@Component
public class ComputerRowMapper implements RowMapper<ComputerDTO> {
	
	private ComputerMapper computerMapper;

	public ComputerRowMapper(ComputerMapper computerMapper) {
		super();
		this.computerMapper = computerMapper;
	}

	@Override
	public ComputerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		return this.computerMapper.computerFromResultSet(rs);
	}
}
