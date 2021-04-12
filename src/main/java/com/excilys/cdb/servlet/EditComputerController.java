package com.excilys.cdb.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.model.mapper.ComputerMapper;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/editComputer")
public class EditComputerController {
	
	private ComputerService servComputer;
	private CompanyService servCompany;
	private ComputerMapper computerMapper;
	
	private ModelAndView mv;
	
	public EditComputerController(ComputerService computerService, CompanyService companyService, ComputerMapper computerMapper){
		this.servComputer = computerService;
		this.servCompany = companyService;
		this.computerMapper = computerMapper;
		this.mv = new ModelAndView("addComputer");
	}
	
	public ModelAndView editComputer() {
				
		return this.getModelAndView();
	}
	
	public ModelAndView getModelAndView() {
		return mv;
	}

}
