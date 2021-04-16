package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.InvalidValuesException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.builder.CompanyBuilder;
import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.validator.CompanyValidator;

@Component
public class CompanyMapper {

	protected static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

	private CompanyValidator companyValidator;

	public CompanyMapper(CompanyValidator companyValidator) {
		super();
		this.companyValidator = companyValidator;
	}

	public Company toCompany(CompanyDTO c) {

		Company company = null;

		try {
			companyValidator.validate(c);
			company = new CompanyBuilder()
					.setId(Integer.parseInt(c.getId()))
					.setName(c.getName())
					.build();
		} catch (InvalidValuesException e) {
			logger.error(e.getMessage());
		}
		return company;	
	}

	public CompanyDTO toCompanyDTO(Company c) {
		return new CompanyDTO(""+c.getId(),c.getName());
	}

	public CompanyDTO companyDTOFromResultSet(ResultSet result) throws SQLException {

		CompanyDTO company = null;

		company = new CompanyDTO(""+result.getInt("id"),result.getString("name"));

		try {
			companyValidator.validate(company);

		} catch (InvalidValuesException e) {
			logger.error(e.getMessage());
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
