package com.excilys.cdb.ui;

import org.springframework.stereotype.Component;

import com.excilys.cdb.controller.CompanyController;
import com.excilys.cdb.controller.ComputerController;

@Component
public class CLIMenu extends CLI {
	
	private CLICompany cliCompany;
	private CLIComputer cliComputer;

	public CLIMenu(CompanyController ctrlCompany, ComputerController ctrlComputer, CLICompany cliCompany,
			CLIComputer cliComputer) {
		super(ctrlCompany, ctrlComputer);
		this.cliCompany = cliCompany;
		this.cliComputer = cliComputer;
	}

	private static final String LEFT_MENU = "You left the menu.";

	public void useMenu() {

		boolean isOver = false;

		while (!isOver) {
			isOver = this.chooseMenu();
		}
		this.sc.close();
	}

	private boolean chooseMenu() {

		this.afficherMenu();

		String input = "";
		boolean inputRight = false;

		while (!inputRight) {
			input = this.useString();
			inputRight = true;

			switch (input) {
			case "1":
				this.cliComputer.useMenuComputer();
				break;
			case "2" :
				this.cliCompany.useMenuCompany();
				break;
			case "3":
				System.out.println(LEFT_MENU);
				return true;
			default:
				inputRight = false;
				logger.error(WRONG_INPUT);
				break;
			}
		}
		return false;
	}
	
	public void afficherMenu() {
		
		System.out.println();
		System.out.println("+--------------------------+");
		System.out.println("|         Main Menu        |");
		System.out.println("+--------------------------+");
		System.out.println("| 1.   Work on computers   |");
		System.out.println("+--------------------------+");
		System.out.println("| 2.   Work on companies   |");
		System.out.println("+--------------------------+");
		System.out.println("| 3.         Quit          |");
		System.out.println("+--------------------------+");
	}
}
