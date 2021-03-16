package com.excilys.cdb.ui;

import com.excilys.cdb.controller.CompanyController;

public class CLICompany extends CLI {
	
	private CompanyView viewCompany = new CompanyView();
	private CompanyController ctrlCompany = new CompanyController();
	
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
				return true;
			default:
				inputRight = false;
				System.out.println(WRONG_INPUT);
				break;
			}
		}
		return false;
	}

	private void findCompanyById() {

		int input = -1;
		boolean inputRight = false;

		while (!inputRight) {

			System.out.println(ID_RESEARCH);
			input = this.useInt();

			if (input < 0) {
				System.out.println(WRONG_INPUT);
			} else {
				inputRight = true;
				this.viewCompany.print(this.ctrlCompany.loadById(input));

			}
		}		
	}

	private void listAllCompanies() {

		viewCompany.printAll(ctrlCompany.getAll());
	}

}
