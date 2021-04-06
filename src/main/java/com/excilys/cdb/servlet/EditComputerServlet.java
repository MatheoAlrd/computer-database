package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.mapper.ComputerMapper;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Component
@WebServlet("/editcomputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	
	@Autowired
	private CompanyService servCompany;
	@Autowired
	private ComputerService servComputer;
	@Autowired
	private ComputerMapper computerMapper;
	
    public EditComputerServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		List<Company> companies = listCompanies(request);
		request.setAttribute("companies", companies);
		
		String id = request.getParameter("id");
		ComputerDTO computerToEdit = this.findComputer(request,Integer.parseInt(id)).get(0);
		
		request.setAttribute("id", id);
		request.setAttribute("computerName", computerToEdit.getName());
		request.setAttribute("introduced", computerToEdit.getIntroduced());
		request.setAttribute("discontinued", computerToEdit.getDiscontinued());
		request.setAttribute("companyId", computerToEdit.getCompanyId());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editComputer.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("computerId");
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		String companyName = request.getParameter("company.name");
		
		
		try {
			servComputer.update(Integer.parseInt(id), computerMapper
					.toComputer(new ComputerDTO(id, name, introduced, discontinued, companyId,companyName)).orElseThrow());
		} catch(NoSuchElementException e) {
			
		} finally {
			response.sendRedirect("dashboard");
		}
	}

	private List<Company> listCompanies(HttpServletRequest request) {

		return servCompany.findAll();
	}
	
	private List<ComputerDTO> findComputer(HttpServletRequest request, int id) {
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		
		for (Computer c : servComputer.find(id)) {
			computersDTO.add(computerMapper.toComputerDTO(c));
		}
		return computersDTO;
	}

}
