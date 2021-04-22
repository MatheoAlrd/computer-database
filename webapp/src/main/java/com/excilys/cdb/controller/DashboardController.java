package com.excilys.cdb.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.model.Page;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.service.ComputerService;

@RestController
@RequestMapping("/dashboard")
@SessionAttributes({"search","currentPage"})
public class DashboardController {

	private ComputerService computerService;

	private ModelAndView mv = new ModelAndView("dashboard");
	
	private Page<ComputerDTO> currentPage = new Page<ComputerDTO>();
	private String search = "";

	public DashboardController(ComputerService computerService) {
		this.computerService = computerService;
	}

	public void setSearch(String search) {
		if(search != null || search == "") {			
			this.search = search;
			this.currentPage.setCurrentPage(1);
		}
	}
	
	public void setPage(int page, int pageSize, String sort) {			
		if(page != -1){
			this.currentPage.setCurrentPage(page);
		}

		if(pageSize != -1){
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
	public ModelAndView listComputers(@RequestParam(name="search", required=false) String search,
			@RequestParam(name="page", defaultValue="-1") int page,
			@RequestParam(name="pageSize", defaultValue="-1") int pageSize,
			@RequestParam(name="sort", required=false) String sort){
				
		this.setSearch(search);
		this.setPage(page, pageSize, sort);	
		
		Sort currentSort = 	Sort.by(Order.asc(this.currentPage.getSort()));

		if(!this.currentPage.isAsc()) {
			currentSort = Sort.by(Order.desc(this.currentPage.getSort()));
		}
		
		Pageable currentPageable = PageRequest.of(this.currentPage.getCurrentPage()-1, this.currentPage.getPageSize(), currentSort);
	
		this.currentPage.setDataList(this.computerService.findPageOrderBy("%"+this.search+"%", currentPageable));
		this.currentPage.setTotalRecord((int) this.computerService.count("%"+this.search+"%"));

		return this.getModelAndView();
	}
	
	@GetMapping("json")
	public List<ComputerDTO> getComputers(@RequestParam(name="search", defaultValue="") String search,
			@RequestParam(name="page", defaultValue="1") int page,
			@RequestParam(name="pageSize", defaultValue="10") int pageSize,
			@RequestParam(name="sort", defaultValue="id") String sort,
			@RequestParam(name="asc", defaultValue="asc") String asc){
		
		Sort currentSort = 	Sort.by(Order.asc(sort));
		if(asc.equals("desc")) {
			currentSort = Sort.by(Order.desc(sort));
		}		
		Pageable currentPageable = PageRequest.of(page-1, pageSize, currentSort);
		
		return this.computerService.findPageOrderBy("%"+search+"%", currentPageable);
	}

	@PostMapping("")
	public ModelAndView deleteComputers(String selection){
		String idSelected[] = selection.split(",");
		for(String id : idSelected)
			computerService.delete(Integer.parseInt(id));

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
