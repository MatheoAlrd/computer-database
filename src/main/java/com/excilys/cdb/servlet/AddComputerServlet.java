package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.mapper.ComputerMapper;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Component
@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CompanyService servCompany;
	private ComputerService servComputer;
	private ComputerMapper computerMapper;

	@Override
	public void init(ServletConfig config) throws ServletException{
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Company> companies = listCompanies(request);
		request.setAttribute("companies", companies);

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		try {
			servComputer.create(computerMapper
					.toComputer(new ComputerDTO("0",name, introduced, discontinued, companyId,"")));
		} catch(NoSuchElementException e) {
			
		} finally {
			response.sendRedirect("/cdb");
		}
	}

	private List<Company> listCompanies(HttpServletRequest request) {
		return servCompany.findAll();
	}

	@Autowired
	public void setServCompany(CompanyService servCompany) {
		this.servCompany = servCompany;
	}
	
	@Autowired
	public void setServComputer(ComputerService servComputer) {
		this.servComputer = servComputer;
	}
	
	@Autowired
	public void setComputerMapper(ComputerMapper computerMapper) {
		this.computerMapper = computerMapper;
	}
}
