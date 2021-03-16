
package com.excilys.cdb.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;

public class CompanyDAO extends DAO<Company> {
	
	private static CompanyDAO instance;

	private CompanyDAO() {
		super();
	}

	public Company find(int id) throws SQLException {

		ResultSet result = this.sConn.getConnection()
				.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE id = " + id);

		if (result.first()) {
			return new Company(id, result.getString("name"));
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
				companies.add(new Company(result.getInt("id"),
						result.getString("name")));
			}


		} catch (SQLException e) {
			e.printStackTrace();
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
