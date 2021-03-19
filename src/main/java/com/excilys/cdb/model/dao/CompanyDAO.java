
package com.excilys.cdb.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.mapper.CompanyMapper;

public class CompanyDAO extends DAO<Company> {
	
	private static final String SELECT_BY_ID_QUERY = "SELECT id, name FROM company WHERE id = ?";
	private static final String SELECT_ALL_QUERY = "SELECT id, name FROM company";

	private static CompanyDAO instance;
	private CompanyMapper companyMapper = new CompanyMapper();


	private CompanyDAO() {
		super();
		logger = LoggerFactory.getLogger(CompanyDAO.class);
	}

	public Company find(int id) {

		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_BY_ID_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, id);
			
			ResultSet result = ps.executeQuery();

			if (result.first()) {
				return this.companyMapper.companyFromResultSet(result);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}

		return null;
	}

	public List<Company> findAll() {

		List<Company> companies = new ArrayList<Company>();
		
		try {
			this.openConnection();
			ResultSet result = this.getConnection()
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY).executeQuery(SELECT_ALL_QUERY);

			while  (result.next()) {
				companies.add(this.companyMapper.companyFromResultSet(result));
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
