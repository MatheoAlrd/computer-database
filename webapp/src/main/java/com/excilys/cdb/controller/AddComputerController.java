package com.excilys.cdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/addComputer")
public class AddComputerController {
	
	private ComputerService computerService;
	private CompanyService companyService;
	private ComputerMapper computerMapper;
	
	private ModelAndView mv = new ModelAndView("addComputer");;
	
	public AddComputerController(ComputerService computerService, CompanyService companyService, ComputerMapper computerMapper){
		this.computerService = computerService;
		this.companyService = companyService;
		this.computerMapper = computerMapper;
	}
	
	@GetMapping("")
	public ModelAndView addComputerGet() {
		this.mv.setViewName("addComputer");
		return this.getModelAndView();
	}
	
	@PostMapping("")
	public ModelAndView addComputerPost(String computerName, String introduced, String discontinued, String companyId) {
		computerService.create(computerMapper
				.toComputer(new ComputerDTO(computerName, introduced, discontinued, companyId)));
		
		this.mv.setViewName("redirect:/dashboard");
		return this.getModelAndView();
	}
	
	public ModelAndView getModelAndView() {		
		this.mv.addObject("companies", this.companyService.findAll());

		return mv;
	}

}
