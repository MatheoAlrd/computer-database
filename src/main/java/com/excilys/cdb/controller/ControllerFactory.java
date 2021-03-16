package com.excilys.cdb.controller;

public class ControllerFactory {
	
	/**
	  * Return a CompanyController object for the Controller
	  * @return Controller
	  */
	public CompanyController getCompanyController() {
		return new CompanyController();
	}
	
	/**
	  * Return a ComputerController object for the Controller
	  * @return Controller
	  */
	public ComputerController getComputerController() {
		return new ComputerController();
	}

}
