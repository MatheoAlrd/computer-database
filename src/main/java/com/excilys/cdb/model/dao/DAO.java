package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DAO<T> {

	protected static Logger logger = LoggerFactory.getLogger(DAO.class);

	private static final ResourceBundle DB = ResourceBundle.getBundle("db");
	private static final String DRIVER = DB.getString("db.driver");
	private static final String URL = DB.getString("db.url");
	private static final String USER = DB.getString("db.user");
	private static final String PWD = DB.getString("db.password");

	private SingleConnection sConn = null;

	public DAO() {
		this.openConnection();			
	}
	
	public void openConnection() {
		try {
			Class.forName(DRIVER);
			sConn = SingleConnection.getSingleConnection(DriverManager.getConnection(URL, USER, PWD));
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
	}
	
	public Connection getConnection() {
		return this.sConn.getConnection();
	}
	
	public void closeConnection() {
		this.sConn.close();
	}
}