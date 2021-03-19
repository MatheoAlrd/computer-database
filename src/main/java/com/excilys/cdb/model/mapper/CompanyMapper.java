package com.excilys.cdb.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.builder.CompanyBuilder;

public class CompanyMapper {

	private ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public Map<String,Object> mapFromCompany(Company company){

		return mapper.convertValue(company, Map.class);
	}

	public Company companyFromMap(Map<String,Object> map) {

		return mapper.convertValue(map, Company.class);		
	}

	public List<Company> companyFromResultSet(ResultSet result) throws SQLException {

		List<Company> companies = new ArrayList<Company>();

		while(result.next()) {
			companies.add(new CompanyBuilder()
					.setId(result.getInt("id"))
					.setName(result.getString("name"))
					.build());
		}

		return companies;
	}

}
