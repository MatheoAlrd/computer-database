package com.excilys.cdb.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.InvalidValuesException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.builder.CompanyBuilder;
import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.validator.CompanyValidator;

@Component
public class CompanyMapper {
	
	protected static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

	private CompanyValidator companyValidator;
	
	public CompanyMapper(CompanyValidator companyValidator) {
		super();
		this.companyValidator = companyValidator;
	}

	public Optional<Company> toCompany(CompanyDTO c) {
		
		Optional<Company> company = Optional.empty();
		
		try {
			companyValidator.validate(c);
			company = Optional.of(new CompanyBuilder()
					.setId(Integer.parseInt(c.getID()))
					.setName(c.getName())
					.build());
		} catch (InvalidValuesException e) {
			logger.error(e.getMessage());
		}
		return company;	
	}
	
	public CompanyDTO toCompanyDTO(Company c) {
		return new CompanyDTO(""+c.getId(),c.getName());
	}
	
	public Optional<CompanyDTO> companyDTOFromResultSet(ResultSet result) throws SQLException {

		Optional<CompanyDTO> company = Optional.empty();;

		while(result.next()) {
			CompanyDTO c = new CompanyDTO(""+result.getInt("id"),result.getString("name"));
			
			try {
				companyValidator.validate(c);
				company = Optional.of(c);
			} catch (InvalidValuesException e) {
				logger.error(e.getMessage());
			}
		}
		return company;
	} 

	public List<CompanyDTO> companiesDTOFromResultSet(ResultSet result) throws SQLException {

		List<CompanyDTO> companies = new ArrayList<CompanyDTO>();

		while(result.next()) {
			CompanyDTO c = new CompanyDTO(""+result.getInt("id"),result.getString("name"));
			
			try {
				companyValidator.validate(c);
				companies.add(c);			
			} catch (InvalidValuesException e) {
				logger.error(e.getMessage());
			}
		}
		return companies;
	}

}
