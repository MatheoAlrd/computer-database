package com.excilys.cdb.model.mapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.InvalidValuesException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.builder.CompanyBuilder;
import com.excilys.cdb.model.builder.ComputerBuilder;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.validator.ComputerValidator;

@Component
public class ComputerMapper {

	protected static Logger logger = LoggerFactory.getLogger(ComputerMapper.class);
	
	private ComputerValidator computerValidator;

	public ComputerMapper(ComputerValidator computerValidator) {
		super();
		this.computerValidator = computerValidator;
	}

	public Optional<Computer> toComputer(ComputerDTO c) {

		Optional<Computer> computer = Optional.empty();
		try {
			computerValidator.validate(c);
			computer = Optional.of(new ComputerBuilder()
					.setId(Integer.parseInt(c.getId()))
					.setName(c.getName())
					.setIntroduced(c.getIntroduced() == null || "".equals(c.getIntroduced()) ? null : LocalDate.parse(c.getIntroduced()))
					.setDiscontinued(c.getDiscontinued() == null || "".equals(c.getDiscontinued()) ? null : LocalDate.parse(c.getDiscontinued()))
					.setCompany(c.getCompanyId() == null || "0".equals(c.getCompanyId()) ? null :new CompanyBuilder()
							.setId(Integer.parseInt(c.getCompanyId()))
							.setName(c.getCompanyName())
							.build())
					.build());
		} catch (InvalidValuesException e) {
			logger.error("Didn't transform Computer DTO Computer because values were invalid \n\t"+e.getMessage());
		}

		return computer;
	}

	public ComputerDTO toComputerDTO(Computer c) {
		String introduced = c.getIntroduced() == null ? null : c.getIntroduced().toString();
		String discontinued = c.getDiscontinued() == null ? null : c.getDiscontinued().toString();
		String companyId = c.getCompany() == null ? null : ""+c.getCompany().getId();
		String companyName = c.getCompany() == null ? null : c.getCompany().getName();
		
		
		return new ComputerDTO(""+c.getId(),c.getName(),introduced,discontinued,companyId,companyName);

	}

	public Optional<ComputerDTO> computerFromResultSet(ResultSet result) {

		Optional<ComputerDTO> computerDTO = Optional.empty();
		String id,name,introduced,discontinued,companyId,companyName;
		try {
			while (result.next()) {
				
				id = ""+result.getInt("computer.id");
				
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
				
				if(result.getString("company.name") == null) {
					companyName = null;
				} else {
					companyName = result.getString("company.name");
				}
				
				ComputerDTO c = new ComputerDTO(id,name, introduced, discontinued, companyId, companyName);
				computerValidator.validate(c);
				computerDTO = Optional.of(c);		
			}
		} catch (InvalidValuesException e) {
			logger.error("Didn't transform ResultSet because values were Invalid \n\t"+e.getMessage());
		} catch (SQLException e) {
			logger.error("Didn't transform ResultSet because there is an SQL Exception \n\t"+e.getMessage());
		}
		return computerDTO;
	}
	
	public List<ComputerDTO> computersFromResultSet(ResultSet result) {

		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		String id,name,introduced,discontinued,companyId,companyName;
		try {
			while (result.next()) {
				
				id = ""+result.getInt("computer.id");
				
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
				
				if(result.getString("company.name") == null) {
					companyName = null;
				} else {
					companyName = result.getString("company.name");
				}
				
				ComputerDTO c = new ComputerDTO(id,name, introduced, discontinued, companyId, companyName);
				computerValidator.validate(c);
				computersDTO.add(c);			
			}
		} catch (InvalidValuesException e) {
			logger.error("Didn't transform ResultSet because values were Invalid \n\t"+e.getMessage());
		} catch (SQLException e) {
			logger.error("Didn't transform ResultSet because there is an SQL Exception \n\t"+e.getMessage());
		}
		return computersDTO;
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

			if(c.getCompanyId() == null) {
				ps.setObject(4,null);
			} else {
				ps.setInt(4, Integer.parseInt(c.getCompanyId()));
			}

		} catch (SQLException e) {
			logger.error("Didn't change the PreparedStatement because there is a SQL exception \n\t"+e.getMessage());
		}
		return ps;
	}
}
