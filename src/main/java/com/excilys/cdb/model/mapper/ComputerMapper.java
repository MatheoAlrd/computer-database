package com.excilys.cdb.model.mapper;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.excilys.cdb.model.Computer;

public class ComputerMapper {
		
	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> mapFromComputer(Computer computer){
				
		return mapper.convertValue(computer, Map.class);
	}
	
	public Computer computerFromMap(Map<String,Object> map) {
		
		return mapper.convertValue(map, Computer.class);		
	}
	


}
