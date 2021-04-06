package com.excilys.cdb.ui;

import org.springframework.stereotype.Component;

import com.excilys.cdb.controller.CompanyController;
import com.excilys.cdb.controller.ComputerController;
import com.excilys.cdb.model.dto.CompanyDTO;

@Component
public class CLICompany extends CLI {
	
	private CompanyView viewCompany;

	public CLICompany(CompanyController ctrlCompany, ComputerController ctrlComputer, CompanyView viewCompany) {
		super(ctrlCompany, ctrlComputer);
		this.viewCompany = viewCompany;
	}

	protected void useMenuCompany() {

		boolean isOver = false;

		while (!isOver) {
			isOver = this.chooseMenuCompany();
		}
	}

	private boolean chooseMenuCompany() {

		this.viewCompany.showMenuCompany();

		String input = "";
		boolean inputRight = false;

		while (!inputRight) {
			input = this.useString();
			inputRight = true;

			switch (input) {
			case "1":
				this.findCompanyById();
				break;
			case "2" :
				this.listAllCompanies();
				break;
			case "3":
				this.create();
				break;
			case "4":
				this.delete();
				break;
			case "5":
				return true;
			default:
				inputRight = false;
				logger.error(WRONG_INPUT);
				break;
			}
		}
		return false;
	}

	private void findCompanyById() {

		System.out.println(ID_RESEARCH);
		this.viewCompany.printAll(this.ctrlCompany.find(this.useInt()));
	}

	private void listAllCompanies() {
		
		viewCompany.printAll(ctrlCompany.findAll());
	}
	
	private void create() {
		System.out.println(ENTER_NAME);
		this.ctrlCompany.create(new CompanyDTO("",this.useString()));
	}
	
	private void delete() {
		System.out.println(ID_RESEARCH);
		this.ctrlCompany.delete(this.useInt());
		
	}

}
