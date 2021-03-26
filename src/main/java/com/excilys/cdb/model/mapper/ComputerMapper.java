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
import com.excilys.cdb.model.dto.CompanyDTO;
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
			computerValidator.validate(c);
			computer = Optional.of(new ComputerBuilder()
					.setName(c.getName())
					.setIntroduced(LocalDate.parse(c.getIntroduced()))
					.setDiscontinued(LocalDate.parse(c.getDiscontinued()))
					.setCompany(new CompanyBuilder()
							.setId(Integer.parseInt(c.getCompanyID()))
							.build())
					.build());
		} catch (InvalidValuesException e) {
			logger.error(e.getMessage());
		}

		return computer;
	}

	public ComputerDTO toComputerDTO(Computer c) {
		String introduced = c.getIntroduced() == null ? null : c.getIntroduced().toString();
		String discontinued = c.getDiscontinued() == null ? null : c.getDiscontinued().toString();
		String companyID = c.getCompany() == null ? null : ""+c.getCompany().getID();

		return new ComputerDTO(c.getName(),introduced,discontinued,companyID);

	}

	public List<ComputerDTO> computersFromResultSet(ResultSet result) throws SQLException {

		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();

		while (result.next()) {

			ComputerDTO c = new ComputerDTO(result.getString("computer.name"),
					result.getDate("computer.introduced").toString(),
					result.getDate("computer.discontinued").toString(),
					result.getObject("computer.company.id").toString());
			
			try {
				computerValidator.validate(c);
				computers.add(c);			
			} catch (InvalidValuesException e) {
				logger.error(e.getMessage());
			}
		}
		return computers;
	}

	public PreparedStatement preparedStatementFromComputer(PreparedStatement ps, ComputerDTO c) throws SQLException {

		ps.setString(1, c.getName());
		
		if(c.getIntroduced() == null) {
			ps.setDate(2, null);
		} else {
			ps.setDate(2, Date.valueOf(c.getIntroduced()));
		}
		
		if(c.getDiscontinued() == null) {
			ps.setDate(3, null);
		} else {
			ps.setDate(3, Date.valueOf(c.getDiscontinued()));
		}
		
		if(c.getCompanyID() == null) {
			ps.setObject(4,null);
		} else {
			ps.setInt(4, Integer.parseInt(c.getCompanyID()));
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
