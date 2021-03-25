package com.excilys.cdb.model.mapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.InvalidValuesException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.builder.CompanyBuilder;
import com.excilys.cdb.model.builder.ComputerBuilder;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.validator.ComputerValidator;

public class ComputerMapper {

	private static ComputerMapper instance;
	private ComputerValidator computerValidator = new ComputerValidator();
	protected static Logger logger = LoggerFactory.getLogger(ComputerMapper.class);


	private ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public Map<String,Object> mapFromComputer(Computer computer){				
		return mapper.convertValue(computer, Map.class);
	}

	public Computer computerFromMap(Map<String,Object> map) {		
		return mapper.convertValue(map, Computer.class);		
	}

	public Optional<Computer> toComputer(ComputerDTO c) {
		
		Optional<Computer> computer = Optional.empty();
		try {
			computerValidator.validateComputer(c);
			computer = Optional.of(new ComputerBuilder()
					.setName(c.getName())
					.setIntroduced(LocalDate.parse(c.getIntroduced()))
					.setDiscontinued(LocalDate.parse(c.getDiscontinued()))
					.setCompany(new CompanyBuilder().setId(Integer.parseInt(c.getCompanyID())).build())
					.build());
		} catch (InvalidValuesException e) {
			logger.error(e.getMessage());
		}
		
		return computer;
	}

	public List<Computer> computersFromResultSet(ResultSet result) throws SQLException {

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

	public static ComputerMapper getInstance() {
		if(instance == null) {
			instance = new ComputerMapper();
		}
		return instance;
	}
}
