package com.excilys.cdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/editComputer")
public class EditComputerController {
	
	private ComputerService computerService;
	private CompanyService companyService;
	private ComputerMapper computerMapper;
	
	private ModelAndView mv = new ModelAndView("editComputer");
	
	private int id = 0;
	
	public EditComputerController(ComputerService computerService, CompanyService companyService, ComputerMapper computerMapper){
		this.computerService = computerService;
		this.companyService = companyService;
		this.computerMapper = computerMapper;
	}
	
	private void setId(int id) {
		if(id != -1) {
			this.id = id;
		}
	}
	
	@GetMapping("")
	public ModelAndView editComputerGet(@RequestParam(name = "id", defaultValue = "-1") String id) {
		
		this.setId(Integer.parseInt(id));
		
		ComputerDTO computerToEdit = this.computerService.find(this.id);
		
		this.mv.addObject("computerName", computerToEdit.getName());
		this.mv.addObject("introduced", computerToEdit.getIntroduced());
		this.mv.addObject("discontinued", computerToEdit.getDiscontinued());
		this.mv.addObject("companyId", computerToEdit.getCompanyId());
		
		this.mv.setViewName("editComputer");
		return this.getModelAndView();
	}
	
	@PostMapping("")
	public ModelAndView editComputerPost(String computerName, String introduced, String discontinued, String companyId) {
		computerService.update(computerMapper
				.toComputer(new ComputerDTO(""+this.id, computerName, introduced, discontinued, companyId, "")));
		
		this.mv.setViewName("redirect:/dashboard");
		return this.getModelAndView();
	}
	
	public ModelAndView getModelAndView() {		
		this.mv.addObject("companies", this.companyService.findAll());		

		return mv;
	}

}
