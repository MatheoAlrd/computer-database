package com.excilys.cdb.model.mapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.builder.CompanyBuilder;
import com.excilys.cdb.model.builder.ComputerBuilder;

public class ComputerMapper {

	private ObjectMapper mapper = new ObjectMapper();
	//private ComputerValidator computerValidator = new ComputerValidator();

	@SuppressWarnings("unchecked")
	public Map<String,Object> mapFromComputer(Computer computer){				
		return mapper.convertValue(computer, Map.class);
	}

	public Computer computerFromMap(Map<String,Object> map) {		
		return mapper.convertValue(map, Computer.class);		
	}

	public List<Computer> computerFromResultSet(ResultSet result) throws SQLException {

		List<Computer> computers = new ArrayList<Computer>();

		while (result.next()) {
			
			ComputerBuilder computerBuilder = new ComputerBuilder()
					.setId(result.getInt("computer.id"))
					.setName(result.getString("computer.name"));

			if (result.getDate("computer.introduced") != null) {
				computerBuilder.setIntroduced(result.getDate("computer.introduced").toLocalDate());
			}
			if (result.getDate("computer.discontinued") != null) {
				computerBuilder.setDiscontinued(result.getDate("computer.discontinued").toLocalDate());
			}
			
			if (result.getObject("computer.company_id") != null && result.getString("company.name") != null) {
				computerBuilder.setCompany(new CompanyBuilder()
						.setId(result.getInt("computer.company_id"))
						.setName(result.getString("company.name"))
						.build());
			}
			
			computers.add(computerBuilder.build());	
		}
		return computers;
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
