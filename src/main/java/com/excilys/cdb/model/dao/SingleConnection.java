package com.excilys.cdb.model.dao;

import java.sql.Connection;

public final class SingleConnection {
	
	private static SingleConnection instance;
	private Connection connection;
	
	private SingleConnection(Connection conn) {
		this.connection = conn;
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

}
