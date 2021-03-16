package com.excilys.cdb.model.mapper;

import java.util.HashMap;
import java.util.Map;

import com.excilys.cdb.model.Computer;

public class ComputerMapper {
		
	public Map<String,Object> mapfromCompany(Computer c){
		
		Map<String,Object> computerMap = new HashMap<String,Object>();
		
		computerMap.put("id",c.getId());
		computerMap.put("name",c.getName());
		computerMap.put("introduced",c.getIntroduced());
		computerMap.put("name",c.getDiscontinued());
		computerMap.put("name",c.getCompany());
		
		return computerMap;
	}

}
