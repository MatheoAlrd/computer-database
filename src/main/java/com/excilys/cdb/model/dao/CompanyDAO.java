
package com.excilys.cdb.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.builder.CompanyBuilder;

public class CompanyDAO extends DAO<Company> {

	private static CompanyDAO instance;

	private CompanyDAO() {
		super();
		logger = LoggerFactory.getLogger(CompanyDAO.class);
	}

	public Company find(int id) {

		try {
			this.openConnection();
			ResultSet result = this.getConnection()
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE id = " + id);

			if (result.first()) {
				return new CompanyBuilder()
						.setId(result.getInt("id"))
						.setName(result.getString("name"))
						.build();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}

		return null;
	}

	public List<Company> getAll() {

		List<Company> companies = new ArrayList<Company>();
		
		try {
			this.openConnection();
			ResultSet result = this.getConnection()
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company");

			while  (result.next()) {
				companies.add(new CompanyBuilder()
						.setId(result.getInt("id"))
						.setName(result.getString("name"))
						.build());
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return companies;
	}

	public static CompanyDAO getInstance() {
		if (instance == null) {
			instance = new CompanyDAO();
		}		
		return instance;
	}

}
