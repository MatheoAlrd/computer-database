package com.excilys.cdb.servlet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.mapper.ComputerMapper;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private ComputerService servComputer;
	private ComputerMapper computerMapper;

	private ModelAndView mv;
	private Page<ComputerDTO> page;

	public DashboardController(ComputerService computerService, ComputerMapper computerMapper) {
		this.servComputer = computerService;
		this.computerMapper = computerMapper;
		this.mv = new ModelAndView("dashboard");
		this.page = new Page<ComputerDTO>();
	}

	@GetMapping("")
	public ModelAndView listComputersGet(@RequestParam(name ="search", defaultValue = "") String search){

		List<Computer> computers = new ArrayList<Computer>();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		computers = servComputer.findPageOrderBy(search, this.page);


		for(Computer c : computers) {
			computersDTO.add(computerMapper.toComputerDTO(c));
		}

		this.page.setDataList(computersDTO);
		this.page.setTotalRecord(this.servComputer.count(search));

		return this.getModelAndView();
	}	

	@PostMapping("")
	public ModelAndView listComputersPost(String selection){
		String idSelected[] = selection.split(",");
		for(String id : idSelected)
			servComputer.delete(Integer.parseInt(id));
		
		return this.getModelAndView();
	}

	public ModelAndView getModelAndView() {

		this.mv.addObject("computers", this.page.getDataList());
		this.mv.addObject("totalComputers", this.page.getTotalRecord());

		this.mv.addObject("previousPage",this.page.previousPage());
		this.mv.addObject("nextPage",this.page.nextPage());

		if(this.page.getCurrentPage() - 1 < 1) {
			this.mv.addObject("pageStart",1);
		} else {
			this.mv.addObject("pageStart", this.page.getCurrentPage() - 1);
		}

		if(this.page.getCurrentPage() + 1 > this.page.getTotalPage()) {
			this.mv.addObject("pageEnd", this.page.getTotalPage());
		} else {
			this.mv.addObject("pageEnd", this.page.getCurrentPage() + 1);
		}

		return mv;
	}

}
