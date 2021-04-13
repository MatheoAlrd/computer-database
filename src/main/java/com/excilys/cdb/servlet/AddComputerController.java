package com.excilys.cdb.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.mapper.ComputerMapper;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/addComputer")
public class AddComputerController {
	
	private ComputerService servComputer;
	private CompanyService servCompany;
	private ComputerMapper computerMapper;
	
	private ModelAndView mv;
	
	public AddComputerController(ComputerService computerService, CompanyService companyService, ComputerMapper computerMapper){
		this.servComputer = computerService;
		this.servCompany = companyService;
		this.computerMapper = computerMapper;
		this.mv = new ModelAndView("addComputer");
	}
	
	@GetMapping("")
	public ModelAndView addComputerGet() {		
		return this.getModelAndView();
	}
	
	@PostMapping("")
	public ModelAndView addComputerPost(String computerName, String introduced, String discontinued, String companyId) {
		servComputer.create(computerMapper
				.toComputer(new ComputerDTO(computerName, introduced, discontinued, companyId)));
		
		this.mv.setViewName("redirect:/dashboard");
		return this.getModelAndView();
	}
	
	public ModelAndView getModelAndView() {		
		this.mv.addObject("companies", this.servCompany.findAll());

		return mv;
	}

}
