package com.excilys.cdb.ui;

public class CLIMenu extends CLI {

	private Menu menu = new Menu();
	
	private CLICompany cliCompany = new CLICompany();
	private CLIComputer cliComputer = new CLIComputer();

	private static final String LEFT_MENU = "You left the menu.";

	public void useMenu() {

		boolean isOver = false;

		while (!isOver) {
			isOver = this.chooseMenu();
		}

		this.sc.close();
	}

	private boolean chooseMenu() {

		this.menu.afficherMenu();

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
}
