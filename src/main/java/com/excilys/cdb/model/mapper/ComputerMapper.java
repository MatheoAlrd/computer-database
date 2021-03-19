package com.excilys.cdb.model.mapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.builder.ComputerBuilder;
import com.excilys.cdb.model.dao.CompanyDAO;

public class ComputerMapper {
		
	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> mapFromComputer(Computer computer){				
		return mapper.convertValue(computer, Map.class);
	}
	
	public Computer computerFromMap(Map<String,Object> map) {		
		return mapper.convertValue(map, Computer.class);		
	}
	
	public Computer computerFromResultSet(ResultSet result) throws SQLException {
		
		ComputerBuilder computerBuilder = new ComputerBuilder()
				.setId(result.getInt("id"))
				.setName(result.getString("name"));

		if (result.getDate("introduced") != null) {
			computerBuilder.setIntroduced(result.getDate("introduced").toLocalDate());
		}
		if (result.getDate("discontinued") != null) {
			computerBuilder.setDiscontinued(result.getDate("discontinued").toLocalDate());
		}
		if (result.getObject("company_id") != null) {
			computerBuilder.setCompany(CompanyDAO.getInstance().find(result.getInt("company_id")));
		}			
		return computerBuilder.build();		
	}
	
	public PreparedStatement preparedStatementFromComputer(PreparedStatement ps, Computer c) throws SQLException {
		
		ps.setObject(1, c.getName());

		if (c.getIntroduced().isEmpty()) {
			ps.setDate(2, null);
		} else {
			ps.setDate(2, Date.valueOf(c.getIntroduced().get()));
		}
		if (c.getDiscontinued().isEmpty()) {
			ps.setDate(3, null);
		} else {
			ps.setDate(3, Date.valueOf(c.getDiscontinued().get()));
		}
		if (c.getCompany().isEmpty()) {
			ps.setObject(4, null);
		} else {
			ps.setInt(4, c.getCompany().get().getId());
		}
		
		return ps;
	}
}
