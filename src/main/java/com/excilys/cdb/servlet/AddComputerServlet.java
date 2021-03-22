package com.excilys.cdb.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.cdb.controller.CompanyController;
import com.excilys.cdb.controller.ComputerController;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.builder.CompanyBuilder;
import com.excilys.cdb.model.builder.ComputerBuilder;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CompanyService servCompany = new CompanyService();
	private ComputerService servComputer = new ComputerService();

       
    public AddComputerServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		List<Company> companies = listCompanies(request, session);
		request.setAttribute("companies", companies);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("discontinued");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");

		servComputer.create(servComputer
				.validateComputer(name, introduced, discontinued, companyId));
	}
	
	private List<Company> listCompanies(HttpServletRequest request, HttpSession session) {

		return servCompany.findAll();
	}

}
