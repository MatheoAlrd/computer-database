package com.excilys.cdb.model.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.ui.CLI;

public abstract class DAO<T> {
	
	protected static final Logger logger = LoggerFactory.getLogger(CLI.class);

	private static final ResourceBundle DB = ResourceBundle.getBundle("db");
	private static final String DRIVER = DB.getString("db.driver");
	private static final String URL = DB.getString("db.url");
	private static final String USER = DB.getString("db.user");
	private static final String PWD = DB.getString("db.password");

	protected SingleConnection sConn = null;

	public DAO() {
		try {
			Class.forName(DRIVER);
			sConn = SingleConnection.getSingleConnection(DriverManager.getConnection(URL, USER, PWD));
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage());
		}			
	}

	public DAO(SingleConnection sConn) {
		this.sConn = sConn;
	}

	public void finalize() {
		try {
			this.sConn.getConnection().close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

}