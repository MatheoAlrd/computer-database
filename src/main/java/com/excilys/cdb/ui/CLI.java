package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.excilys.cdb.controller.CompanyController;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.builder.ComputerBuilder;

public abstract class CLI {
	
	protected Scanner sc = new Scanner(System.in);
	
	protected static final String WRONG_INPUT = "Wrong Input";
	protected static final String WRONG_INPUT_TYPE = "Wrong Input Type";
	
	protected static final String DATE_BEFORE = "Wrong Date : Discontinued is before Introduced";
	protected static final String DATE_FORMAT = "yyyy-mm-dd";
	protected static final String ENTER_DATE = "Enter the date, format: " + DATE_FORMAT;
	protected static final String ENTER_NAME = "Enter a name";
	protected static final String ENTER_ID = "Enter a valid id";
	
	protected static final String CONTINUE = "Continue ? (y/n)";

	protected static final String ID_RESEARCH = "Which id are you looking for ?";

	protected static final String COMMAND_GOOD = "Command executed successfully.";
	protected static final String COMMAND_BAD = "Command not executed.";
	
	protected Computer useComputer() {

		ComputerBuilder computerBuilder = new ComputerBuilder();
		CompanyController ctrlCompany = new CompanyController();

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
			//computerBuilder.setCompany(ctrlCompany.loadById(companyId));
		}					

		return computerBuilder.build();
	}

	protected int useInt() {

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

	protected String useString() {

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
