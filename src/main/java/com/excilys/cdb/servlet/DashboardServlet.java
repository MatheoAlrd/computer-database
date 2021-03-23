package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.cdb.controller.ComputerController;
import com.excilys.cdb.model.Computer;

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerController ctrlComputer = new ComputerController();	

	public DashboardServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<Computer> computers = listComputers(request, session);
		request.setAttribute("computers", computers);		

		String totalComputers = computers.size()+" ";
		request.setAttribute("totalComputers", totalComputers);

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private List<Computer> listComputers(HttpServletRequest request, HttpSession session) {

		List<Computer> computers = new ArrayList<Computer>();

		String search = request.getParameter("search");
		if(search == null || search.equals("#")) {
			computers = ctrlComputer.getAll();
		} else {
			try {
			computers = ctrlComputer.find(Integer.parseInt(search));
			} catch (NumberFormatException e) {
				computers = ctrlComputer.find(search);
			}
		}
		return computers;
	}

}
