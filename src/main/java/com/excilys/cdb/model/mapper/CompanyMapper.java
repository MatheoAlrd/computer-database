package com.excilys.cdb.model.mapper;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.excilys.cdb.model.Company;

public class CompanyMapper {
	
	private ObjectMapper mapper = new ObjectMapper();
		
	@SuppressWarnings("unchecked")
	public Map<String,Object> mapFromCompany(Company company){
				
		return mapper.convertValue(company, Map.class);
	}
	
	public Company companyFromMap(Map<String,Object> map) {
		
		return mapper.convertValue(map, Company.class);		
	}

}
