package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.excilys.cdb.controller.CompanyController;
import com.excilys.cdb.controller.ComputerController;
import com.excilys.cdb.controller.ControllerFactory;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.builder.ComputerBuilder;

public class UseMenu {

	private Menu menu = new Menu();
	private CompanyView cView = new CompanyView();
	private ComputerView computerView = new ComputerView();

	private Scanner sc = new Scanner(System.in);

	private ControllerFactory ctrlF = new ControllerFactory();	

	//private static final ResourceBundle CLI = ResourceBundle.getBundle("main.ressources.cli");

	private static final String LEFT_MENU = "You left the menu.";

	private static final String WRONG_INPUT = "Wrong Input";
	private static final String WRONG_INPUT_TYPE = "Wrong Input Type";

	private static final String DATE_BEFORE = "Wrong Date : Discontinued is before Introduced";
	private static final String DATE_FORMAT = "yyyy-mm-dd";
	private static final String ENTER_DATE = "Enter the date, format: " + DATE_FORMAT;
	private static final String ENTER_NAME = "Enter a name";
	private static final String ENTER_ID = "Enter a valid id";


	private static final String CONTINUE = "Continue ? (y/n)";

	private static final String ID_RESEARCH = "Which id are you looking for ?";

	private static final String COMMAND_GOOD = "Command executed successfully.";
	private static final String COMMAND_BAD = "Command not executed.";

	public UseMenu() { }

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
				this.useMenuComputer();
				break;
			case "2" :
				this.useMenuCompany();
				break;
			case "3":
				System.out.println(LEFT_MENU);
				return true;
			default:
				inputRight = false;
				System.out.println(WRONG_INPUT);
				break;
			}
		}
		return false;
	}

	private void useMenuCompany() {

		boolean isOver = false;

		while (!isOver) {
			isOver = this.chooseMenuCompany();
		}
	}

	private boolean chooseMenuCompany() {

		this.menu.afficherMenuCompany();

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

		CompanyController ctrlCompany = ctrlF.getCompanyController();

		int input = -1;
		boolean inputRight = false;

		while (!inputRight) {

			System.out.println(ID_RESEARCH);
			input = this.useInt();

			if (input < 0) {
				System.out.println(WRONG_INPUT);
			} else {
				inputRight = true;
				cView.print(ctrlCompany.loadById(input));

			}
		}		
	}

	private void listAllCompanies() {

		CompanyController ctrlCompany = ctrlF.getCompanyController();

		cView.printAll(ctrlCompany.getAll());

	}

	private void useMenuComputer() {

		boolean isOver = false;

		while (!isOver) {
			isOver = this.chooseMenuComputer();
		}

	}

	private boolean chooseMenuComputer() {

		boolean inputRight = false;
		String input = "";

		this.menu.afficherMenuComputer();

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

		ComputerController ctrlComputer = ctrlF.getComputerController();

		if (ctrlComputer.create(this.useComputer())) {
			System.out.println(COMMAND_GOOD);
		} else {
			System.out.println(COMMAND_BAD);
		}
	}

	private void deleteComputer() {

		ComputerController ctrlComputer = ctrlF.getComputerController();

		int input = -1;

		boolean stop = false;

		while (!stop) {

			System.out.println(ID_RESEARCH);
			input = this.useInt();

			if (ctrlComputer.delete(input)) {
				System.out.println(COMMAND_GOOD);
				stop = true;
			} else  {
				System.out.println(COMMAND_BAD);
				stop = !this.askContinue();
			}
		}
	}

	private void updateComputer() {

		ComputerController ctrlComputer = ctrlF.getComputerController();

		int input = -1;
		System.out.println(ID_RESEARCH);
		input = this.useInt();

		ctrlComputer.update(input, this.useComputer());
		System.out.println(COMMAND_GOOD);
	}




	private void findComputerById() {

		ComputerController ctrlComputer = ctrlF.getComputerController();

		System.out.println(ID_RESEARCH);
		computerView.print(ctrlComputer.loadById(this.useInt()));
	}	

	private void listAllComputers() {

		ComputerController ctrlComputer = ctrlF.getComputerController();

		computerView.printAll(ctrlComputer.getAll());
	}

	private boolean askContinue() {

		boolean inputRight = false;
		boolean inputTypeRight = false;
		String input = "";

		while (!inputRight || !inputTypeRight) {
			try {

				System.out.println(CONTINUE);
				input = this.sc.nextLine();
				inputTypeRight = true;

				switch (input) {
				case "y":
					return true;
				case "n":
					return false;
				default:
					System.out.println(WRONG_INPUT);
					break;
				}

			} catch (Exception e) {
				System.out.println(WRONG_INPUT_TYPE);
			}
		}

		return false;

	}

	private Computer useComputer() {

		CompanyController ctrlCompany = ctrlF.getCompanyController();
		ComputerBuilder computerBuilder = new ComputerBuilder();

		System.out.println(ENTER_NAME);
		String name = this.useString();
		computerBuilder.setName(name);

		System.out.println(ENTER_DATE + " (introduced)");
		LocalDate introduced = this.useDate();
		computerBuilder.setIntroduced(introduced);

		System.out.println(ENTER_DATE + " (discontinued)");
		LocalDate discontinued = this.useDate();

		if (introduced != null && discontinued != null) {
			while (discontinued.isBefore(introduced)) {
				System.out.println(DATE_BEFORE);
				discontinued = this.useDate();
			}
		}
		computerBuilder.setDiscontinued(discontinued);

		System.out.println(ENTER_ID);
		int companyId = useInt();
		if (companyId != -1) {
			computerBuilder.setCompany(ctrlCompany.loadById(companyId));
		}					

		return computerBuilder.build();
	}

	private int useInt() {

		boolean inputTypeRight = false;
		String input = "";
		int res = -1;

		while (!inputTypeRight) {
			try {

				input = this.sc.nextLine();
				if (input.equals("null")) {
					return -1;
				}
				inputTypeRight = true;

				res = Integer.parseInt(input);

				if (res < 0) {
					System.out.println(WRONG_INPUT);
					inputTypeRight = false;
				} else {
					return res;
				}
			} catch (Exception e) {
				System.out.println(WRONG_INPUT_TYPE);
				inputTypeRight = false;

			}
		}
		return res;
	}

	private String useString() {

		boolean inputTypeRight = false;
		String input = "";

		while (!inputTypeRight) {
			try {
				input = this.sc.nextLine();
				inputTypeRight = true;
			} catch (Exception e) {
				System.out.println(WRONG_INPUT_TYPE);
			}
		}
		return input;
	}

	private LocalDate useDate() {

		String input = "";
		LocalDate tmpDate = null;

		while (tmpDate == null) {
			input = sc.nextLine();
			if (input.equals("null")) {
				return null;
			}

			try {
				tmpDate = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
			} catch (DateTimeParseException e) {
				System.out.println("Date Format non valide");
				System.out.println(ENTER_DATE);
			}
		}
		return tmpDate;
	}

}
