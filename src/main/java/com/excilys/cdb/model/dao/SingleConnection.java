package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.ui.CLI;

public final class SingleConnection {
	
	protected static final Logger logger = LoggerFactory.getLogger(SingleConnection.class);
	
	private static SingleConnection instance;
	private Connection connection;
	
	private SingleConnection(Connection connection) {
		this.connection = connection;
	}
	
	public static SingleConnection getSingleConnection(Connection conn) {
		if (instance == null) {
			instance = new SingleConnection(conn);
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		return this.connection;
	}

	public void close() {
		try {
			this.connection.close();
			instance = null;
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

}
