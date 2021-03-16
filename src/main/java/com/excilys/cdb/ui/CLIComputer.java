package com.excilys.cdb.ui;

import com.excilys.cdb.controller.ComputerController;

public class CLIComputer extends CLI {
	
	private ComputerView viewComputer = new ComputerView();
	private ComputerController ctrlComputer = new ComputerController();
	
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
				System.out.println(WRONG_INPUT);
				break;
			}
		}
		return false;
	}

	private void createComputer() {

		if (this.ctrlComputer.create(this.useComputer())) {
			System.out.println(COMMAND_GOOD);
		} else {
			System.out.println(COMMAND_BAD);
		}
	}

	private void deleteComputer() {

		int input = -1;

		boolean stop = false;

		while (!stop) {

			System.out.println(ID_RESEARCH);
			input = this.useInt();

			if (this.ctrlComputer.delete(input)) {
				System.out.println(COMMAND_GOOD);
				stop = true;
			} else  {
				System.out.println(COMMAND_BAD);
			}
		}
	}

	private void updateComputer() {

		int input = -1;
		System.out.println(ID_RESEARCH);
		input = this.useInt();

		this.ctrlComputer.update(input, this.useComputer());
		System.out.println(COMMAND_GOOD);
	}




	private void findComputerById() {

		System.out.println(ID_RESEARCH);
		viewComputer.print(this.ctrlComputer.loadById(this.useInt()));
	}	

	private void listAllComputers() {

		viewComputer.printAll(this.ctrlComputer.getAll());
	}

}
