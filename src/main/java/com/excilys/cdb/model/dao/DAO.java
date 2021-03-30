package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DAO<T> {

	protected static Logger logger = LoggerFactory.getLogger(DAO.class);

	public DAO() {
	}
	
	public Connection getConnection() throws SQLException {
		return DataSource.getConnection();
	}
}