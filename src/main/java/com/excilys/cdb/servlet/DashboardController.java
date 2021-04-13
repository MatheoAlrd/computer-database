package com.excilys.cdb.servlet;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.mapper.ComputerMapper;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/dashboard")
@SessionAttributes({"search","currentPage"})
public class DashboardController {

	private ComputerService servComputer;
	private ComputerMapper computerMapper;

	private ModelAndView mv;
	
	private Page<ComputerDTO> currentPage;
	private String search;

	public DashboardController(ComputerService computerService, ComputerMapper computerMapper) {
		this.servComputer = computerService;
		this.computerMapper = computerMapper;
		this.mv = new ModelAndView("dashboard");
		this.currentPage = new Page<ComputerDTO>();
		this.search = "";
	}

	public void setSearch(String search) {
		if(search != null) {			
			this.search = search;
		}
	}
	
	public void setPage(int page, int pageSize, String sort) {			
		if(page != 0){
			this.currentPage.setCurrentPage(page);
		}

		if(pageSize != 0){
			this.currentPage.setPageSize(pageSize);
			this.currentPage.setCurrentPage(1);
		}

		if(sort != null) {
			if(sort.equals(this.currentPage.getSort())) {
				this.currentPage.setAsc(!this.currentPage.isAsc());
			} else {
				this.currentPage.setAsc(true);
			}
			
			this.currentPage.setSort(sort);
			this.currentPage.setCurrentPage(1);
		}		
	}

	@GetMapping("")
	public ModelAndView listComputers(@RequestParam(name ="search", required = false) String search,
			@RequestParam(name ="page", defaultValue="0") int page,
			@RequestParam(name="pageSize", defaultValue="0") int pageSize,
			@RequestParam(name="sort", required=false) String sort){
		
		this.setSearch(search);
		this.setPage(page, pageSize, sort);

		this.currentPage.setDataList(this.servComputer.findPageOrderBy(this.search, this.currentPage).stream()
				.map(c -> computerMapper.toComputerDTO(c)).collect(Collectors.toList()));
		this.currentPage.setTotalRecord(this.servComputer.count(this.search));

		return this.getModelAndView();
	}	

	@PostMapping("")
	public ModelAndView deleteComputers(String selection){
		String idSelected[] = selection.split(",");
		for(String id : idSelected)
			servComputer.delete(Integer.parseInt(id));

		return this.getModelAndView();
	}

	public ModelAndView getModelAndView() {
		this.mv.addObject("computers", this.currentPage.getDataList());
		this.mv.addObject("totalComputers", this.currentPage.getTotalRecord());

		this.mv.addObject("previousPage",this.currentPage.previousPage());
		this.mv.addObject("nextPage",this.currentPage.nextPage());
		this.mv.addObject("pageMax",this.currentPage.getTotalPage());

		if(this.currentPage.getCurrentPage() - 1 < 1) {
			this.mv.addObject("pageStart",1);
		} else {
			this.mv.addObject("pageStart", this.currentPage.getCurrentPage() - 1);
		}

		if(this.currentPage.getCurrentPage() + 1 > this.currentPage.getTotalPage()) {
			this.mv.addObject("pageEnd", this.currentPage.getTotalPage());
		} else {
			this.mv.addObject("pageEnd", this.currentPage.getCurrentPage() + 1);
		}

		return mv;
	}

}
