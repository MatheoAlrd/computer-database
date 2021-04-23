package com.excilys.cdb.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.builder.ComputerBuilder;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@RestController
@RequestMapping("apicomputer")
public class APIController {
	
	private ComputerService computerService;
	public CompanyService companyService;
	
	public APIController(ComputerService computerService, CompanyService companyService) {
		this.computerService = computerService;
		this.companyService = companyService;
	}
	
	@GetMapping(value="/list")
	public ResponseEntity<List<ComputerDTO>> getComputers(@RequestParam(name="search", defaultValue="") String search,
			@RequestParam(name="page", defaultValue="1") int page,
			@RequestParam(name="pageSize", defaultValue="10") int pageSize,
			@RequestParam(name="sort", defaultValue="id") String sort,
			@RequestParam(name="asc", defaultValue="asc") String asc){
		
		Sort currentSort = 	Sort.by(Order.asc(sort));
		if(asc.equals("desc")) {
			currentSort = Sort.by(Order.desc(sort));
		}		
		Pageable currentPageable = PageRequest.of(page-1, pageSize, currentSort);
		
		return ResponseEntity.ok(this.computerService.findPageOrderBy("%"+search+"%", currentPageable));
	}
	
	@GetMapping(value="/count")
	public ResponseEntity<Integer> countComputers(@RequestParam(name="search", defaultValue="") String search){	
		return ResponseEntity.ok(this.computerService.count("%"+search+"%"));
	}
	
	@PostMapping(value="/create")
	public ResponseEntity<ComputerDTO> createComputers(@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="introduced", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate introduced,
			@RequestParam(name="discontinued", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate discontinued,
			@RequestParam(name="companyId", defaultValue="0") int companyId){
		
		
		
		return ResponseEntity.ok(this.computerService.create(new ComputerBuilder()
				.setName(name)
				.setIntroduced(introduced)
				.setDiscontinued(discontinued)
				.setCompany(this.companyService.findById(companyId))
				.build()));
	}
	
	@PostMapping(value="/update")
	public ResponseEntity<ComputerDTO> updateComputers(@RequestParam(name="id", defaultValue="0") int id,
			@RequestParam(name="name", defaultValue="") String name,
			@RequestParam(name="introduced", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate introduced,
			@RequestParam(name="discontinued", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate discontinued,
			@RequestParam(name="companyId", defaultValue="0") int companyId){
		return ResponseEntity.ok(this.computerService.update(new ComputerBuilder()
				.setId(id)
				.setName(name)
				.setIntroduced(introduced)
				.setDiscontinued(discontinued)
				.setCompany(this.companyService.findById(companyId))
				.build()));
	}

}