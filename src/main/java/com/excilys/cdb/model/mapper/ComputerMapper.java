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
			computerValidator.validate(c);
			computer = Optional.of(new ComputerBuilder()
					.setName(c.getName())
					.setIntroduced(c.getIntroduced() == null ? null : LocalDate.parse(c.getIntroduced()))
					.setDiscontinued(c.getDiscontinued() == null ? null : LocalDate.parse(c.getDiscontinued()))
					.setCompany(c.getCompanyID() == null ? null : new CompanyBuilder()
							.setId(Integer.parseInt(c.getCompanyID()))
							.build())
					.build());
		} catch (InvalidValuesException e) {
			logger.error("Didn't transform Computer DTO Computer because values were invalid",e);
		}

		return computer;
	}

	public ComputerDTO toComputerDTO(Computer c) {
		String introduced = c.getIntroduced() == null ? null : c.getIntroduced().toString();
		String discontinued = c.getDiscontinued() == null ? null : c.getDiscontinued().toString();
		String companyID = c.getCompany() == null ? null : ""+c.getCompany().getId();

		return new ComputerDTO(c.getName(),introduced,discontinued,companyID);

	}

	public List<ComputerDTO> computersFromResultSet(ResultSet result) {

		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		String name,introduced,discontinued,companyId;
		try {
			while (result.next()) {
				name = result.getString("computer.name");

				if(result.getDate("computer.introduced") == null) {
					introduced = null;
				} else {
					introduced = result.getDate("computer.introduced").toString();
				}

				if(result.getDate("computer.discontinued") == null) {
					discontinued = null;
				} else {
					discontinued = result.getDate("computer.discontinued").toString();
				}

				if(result.getObject("computer.company_id") == null) {
					companyId = null;
				} else {
					companyId = result.getObject("computer.company_id").toString();
				}

				ComputerDTO c = new ComputerDTO(name, introduced, discontinued, companyId);
				computerValidator.validate(c);
				computers.add(c);			
			}
		} catch (InvalidValuesException e) {
			logger.error("Didn't transform ResultSet because values were Invalid \n"+e.getMessage());
		} catch (SQLException e) {
			logger.error("Didn't transform ResultSet because there is an SQL Exception \n"+e.getMessage());
		}
		return computers;
	}

	public PreparedStatement preparedStatementFromComputer(PreparedStatement ps, ComputerDTO c) {

		try {
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

		} catch (SQLException e) {
			logger.error("Didn't change the PreparedStatement because there is a SQL exception \n"+e.getMessage());
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
