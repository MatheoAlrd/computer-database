package com.excilys.cdb.model.mapper;

import java.util.HashMap;
import java.util.Map;

import com.excilys.cdb.model.Company;

public class CompanyMapper {
		
	public Map<String,Object> mapfromCompany(Company c){
		
		Map<String,Object> companyMap = new HashMap<String,Object>();
		
		companyMap.put("id",c.getId());
		companyMap.put("name",c.getName());
		
		return companyMap;
	}

}
