package com.excilys.cdb.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.model.dto.ComputerDTO;
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
		this.mv = new ModelAndView("editComputer");
	}
	
	@GetMapping("")
	public ModelAndView editComputerGet(@RequestParam(name = "id", defaultValue = "0") String id) {
		
		ComputerDTO computerToEdit = this.computerMapper.toComputerDTO(
				this.servComputer.find(Integer.parseInt(id)).get(0));
		
		this.mv.addObject("computerName", computerToEdit.getName());
		this.mv.addObject("introduced", computerToEdit.getIntroduced());
		this.mv.addObject("discontinued", computerToEdit.getDiscontinued());
		this.mv.addObject("companyId", computerToEdit.getCompanyId());
		
		return this.getModelAndView();
	}
	
	@PostMapping("")
	public ModelAndView editComputerPost(String computerId, String computerName, String introduced, String discontinued, String companyId) {
		servComputer.update(Integer.parseInt(computerId), computerMapper
				.toComputer(new ComputerDTO(computerName, introduced, discontinued, companyId)));
		return this.getModelAndView();
	}
	
	public ModelAndView getModelAndView() {
		
		this.mv.addObject("companies", this.servCompany.findAll());		

		return mv;
	}

}
