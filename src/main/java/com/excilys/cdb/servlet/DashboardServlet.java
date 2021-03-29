package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.mapper.ComputerMapper;
import com.excilys.cdb.service.ComputerService;

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService servComputer = new ComputerService();
	private ComputerMapper computerMapper = ComputerMapper.getInstance();

	public DashboardServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		request = this.paginate(request, session);

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String idSelected[] = request.getParameter("selection").split(",");
		try {
			for(String id : idSelected)
				servComputer.delete(Integer.parseInt(id));

		} catch(NoSuchElementException e) {

		} finally {
			response.sendRedirect("dashboard");
		}
		
	
		String search = null;
		if(session.getAttribute("search") != null) {
			search = (String) session.getAttribute("search");
		}
		String currentSearch = request.getParameter("search");
		if(currentSearch != null) {
			search = currentSearch;
		}
		
		int totalComputers = this.countComputers(search);
		request.setAttribute("totalComputers", totalComputers);

		//this.doGet(request,response);
	}

	private List<ComputerDTO> listComputersPage(String search, int pageSize, int offset){

		List<Computer> computers = new ArrayList<Computer>();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		if(search == null) {
			return computersDTO;
		}
		if(search.equals("#")) {
			computers = servComputer.findAllPage(pageSize, offset);
		} else {
			try {
				computers = servComputer.find(Integer.parseInt(search));
			} catch (NumberFormatException e) {
				computers = servComputer.findPage(search, pageSize, offset);
			}
		}
		
		for(Computer c : computers) {
			computersDTO.add(computerMapper.toComputerDTO(c));
		}

		return computersDTO;
	}

	private List<ComputerDTO> listComputerPageOrderBy(String search, int pageSize, int offset, String sort, boolean asc){
		
		List<Computer> computers = new ArrayList<Computer>();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		if(search == null) {
			return computersDTO;
		}
		if(search.equals("#")) {
			computers = servComputer.findAllPageOrderBy(pageSize, offset, sort, asc);
		} else {
			try {
				computers = servComputer.find(Integer.parseInt(search));
			} catch (NumberFormatException e) {
				computers = servComputer.findPageOrderBy(search, pageSize, offset, sort, asc);
			}
		}

		for(Computer c : computers) {
			computersDTO.add(computerMapper.toComputerDTO(c));
		}

		return computersDTO;
	}
	
	private int countComputers(String search) {
		if(search == null) {
			return 0;
		}
		if(search.equals("#")) {
			return servComputer.count();
		} else {
			return servComputer.count(search);
		}

	}

	private HttpServletRequest paginate(HttpServletRequest request, HttpSession session) {

		int page = 1;
		int pageSize = 100;
		String search = null;
		String sort = "computer.id";
		boolean asc = true;

		if(session.getAttribute("page") != null) {
			page = (int) session.getAttribute("page");
		}
		if(session.getAttribute("pageSize") != null) {
			pageSize = (int) session.getAttribute("pageSize");
		}
		if(session.getAttribute("search") != null) {
			search = (String) session.getAttribute("search");
		}
		if(session.getAttribute("sort") != null) {
			sort = (String) session.getAttribute("sort");
		}
		if(session.getAttribute("asc") != null) {
			asc = (boolean) session.getAttribute("asc");
		}

		String spageNum = request.getParameter("page");
		if(spageNum != null) {
			page = Integer.parseInt(spageNum);
			session.setAttribute("page",page);
		}
		String spageSize = request.getParameter("pageSize");
		if(spageSize != null) {
			pageSize = Integer.parseInt(spageSize);
			session.setAttribute("pageSize",pageSize);
			page = 1;
			session.setAttribute("page",page);
		}
		String currentSearch = request.getParameter("search");
		if(currentSearch != null) {
			search = currentSearch;
			session.setAttribute("search",search);
			page = 1;
			session.setAttribute("page",page);
		}
		String currentSort = request.getParameter("sort");
		if(currentSort != null) {
			asc = !currentSort.equals(sort);
			sort = currentSort;
			session.setAttribute("sort",sort);
		}

		int totalComputers = this.countComputers(search);

		List<ComputerDTO> computers = listComputerPageOrderBy(search, pageSize, pageSize*(page-1), sort ,asc);

		Page<ComputerDTO> currentPage = new Page<ComputerDTO>(page, pageSize, totalComputers, computers);

		if(page - 1 < 1) {
			session.setAttribute("pageStart",1);
		} else {
			session.setAttribute("pageStart", page - 1);
		}

		if(page + 1 > currentPage.getTotalPage()) {
			session.setAttribute("pageEnd", currentPage.getTotalPage());
		} else {
			session.setAttribute("pageEnd", page + 1);
		}

		request.setAttribute("pageMax", currentPage.getTotalPage());
		request.setAttribute("previousPage", currentPage.previousPage());
		request.setAttribute("nextPage", currentPage.nextPage());

		request.setAttribute("computers", computers);
		request.setAttribute("totalComputers", totalComputers);

		return request;
	}
}