package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.ComputerService;

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService servComputer = new ComputerService();

	public DashboardServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object[] con = {request,session};
		con = this.paginate(request, session);
		request = (HttpServletRequest) con[0];
		session = (HttpSession) con[1];		
		/*
		System.out.println(request.getParameter("computerNameSelected"));
		System.out.println(request.getParameter("computerIntroducedSelected"));
		System.out.println(request.getParameter("computerDiscontinuedSelected"));
		System.out.println(request.getParameter("computerCompanyNameSelected"));
		 */


		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private List<Computer> listComputersPage(String search, int pageSize, int offset){

		List<Computer> computers = new ArrayList<Computer>();

		if(search == null) {
			return computers;
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

		return computers;
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

	private Object[] paginate(HttpServletRequest request, HttpSession session) {

		int page = 1;
		int pageSize = 100;
		String search = null;

		if(session.getAttribute("page") != null) {
			page = (int) session.getAttribute("page");
		}
		if(session.getAttribute("pageSize") != null) {
			pageSize = (int) session.getAttribute("pageSize");
		}
		if(session.getAttribute("search") != null) {
			search = (String) session.getAttribute("search");
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

		int totalComputers = this.countComputers(search);

		List<Computer> computers = listComputersPage(search,pageSize,pageSize*(page-1));

		Page<Computer> currentPage = new Page<Computer>(page,pageSize,totalComputers,computers);

		if(page - 1 < 1) {
			session.setAttribute("pageStart",1);
		} else {
			session.setAttribute("pageStart",page - 1);
		}

		if(page + 1 > currentPage.getTotalPage()) {
			session.setAttribute("pageEnd",currentPage.getTotalPage());
		} else {
			session.setAttribute("pageEnd",page + 1);
		}

		request.setAttribute("pageMax", currentPage.getTotalPage());
		request.setAttribute("previousPage", currentPage.previousPage());
		request.setAttribute("nextPage", currentPage.nextPage());

		request.setAttribute("computers", computers);
		request.setAttribute("totalComputers", totalComputers);

		Object[] res = {request,session};
		return res;
	}
}