package com.excilys.cdb.model.dao;

public class DAOFactory {
	
	  /**
	  * Return a ComputerDAO object for the DataBase
	  * @return DAO<Computer>
	  */
	  public static ComputerDAO getComputerDAO() {
	    return ComputerDAO.getInstance();
	  }

	  /**
	  * Return a CompanyDAO object for the DataBase
	  * @return DAO<Company>
	  */
	  public static CompanyDAO getCompanyDAO() {
	    return CompanyDAO.getInstance();
	  }

	
	

}
