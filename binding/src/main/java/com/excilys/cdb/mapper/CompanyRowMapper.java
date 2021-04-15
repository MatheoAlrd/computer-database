package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.CompanyDTO;

@Component
public class CompanyRowMapper implements RowMapper<CompanyDTO> {
	
	private CompanyMapper companyMapper;
	
	public CompanyRowMapper(CompanyMapper companyMapper) {
		super();
		this.companyMapper = companyMapper;
	}
	
	@Override
	public CompanyDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		return this.companyMapper.companyDTOFromResultSet(rs);
	}
	

}
