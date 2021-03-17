
package com.excilys.cdb.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.builder.CompanyBuilder;

public class CompanyDAO extends DAO<Company> {

	private static CompanyDAO instance;

	private CompanyDAO() {
		super();
	}

	public Company find(int id) {

		try {
			ResultSet result = this.sConn.getConnection()
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
		}

		return null;
	}

	public List<Company> getAll() {

		List<Company> companies = new ArrayList<Company>();
		
		try {
			ResultSet result = this.sConn.getConnection()
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
