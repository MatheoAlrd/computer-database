package com.excilys.cdb.ui;

import org.springframework.stereotype.Component;

import com.excilys.cdb.controller.CompanyController;
import com.excilys.cdb.controller.ComputerController;

@Component
public class CLIComputer extends CLI {
	
	private ComputerView viewComputer;
		
	public CLIComputer(CompanyController ctrlCompany, ComputerController ctrlComputer, ComputerView viewComputer) {
		super(ctrlCompany, ctrlComputer);
		this.viewComputer = viewComputer;
	}

	protected void useMenuComputer() {

		boolean isOver = false;

		while (!isOver) {
			isOver = this.chooseMenuComputer();
		}
	}

	private boolean chooseMenuComputer() {

		boolean inputRight = false;
		String input = "";

		this.viewComputer.showMenuComputer();

		while (!inputRight) {
			input = this.useString();
			inputRight = true;
			
			switch (input) {
			case "1":
				this.createComputer();
				break;
			case "2":
				this.deleteComputer();
				break;
			case "3":
				this.updateComputer();
				break;
			case "4":
				this.findComputerById();
				break;
			case "5":
				this.listAllComputers();
				break;
			case "6":
				return true;
			default:
				inputRight = false;
				logger.error(WRONG_INPUT);
				break;
			}
		}
		return false;
	}

	private void createComputer() {
		this.ctrlComputer.create(this.useComputer());		
	}

	private void deleteComputer() {
		System.out.println(ID_RESEARCH);
		this.ctrlComputer.delete(this.useInt());
	}

	private void updateComputer() {
		System.out.println(ID_RESEARCH);
		this.ctrlComputer.update(this.useInt(), this.useComputer());
	}

	private void findComputerById() {
		System.out.println(ID_RESEARCH);
		viewComputer.printAll(this.ctrlComputer.find(this.useInt()));
	}	

	private void listAllComputers() {
		viewComputer.printAll(this.ctrlComputer.findAll());
	}

}
