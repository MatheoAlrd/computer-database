
package com.excilys.cdb.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.mapper.CompanyMapper;

public class CompanyDAO extends DAO<CompanyDTO> {

	private static final String SELECT_BY_ID_QUERY = "SELECT id, name FROM company WHERE id = ?";
	private static final String SELECT_ALL_QUERY = "SELECT id, name FROM company";

	private static CompanyDAO instance;
	private CompanyMapper companyMapper = new CompanyMapper();


	private CompanyDAO() {
		super();
		logger = LoggerFactory.getLogger(CompanyDAO.class);
	}

	public List<CompanyDTO> find(int id) {

		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_BY_ID_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, id);
			return this.companyMapper.companyDTOFromResultSet(ps.executeQuery());

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}

		return null;
	}

	public List<CompanyDTO> findAll() {

		List<CompanyDTO> companies = new ArrayList<CompanyDTO>();

		try {
			this.openConnection();
			ResultSet result = this.getConnection()
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY).executeQuery(SELECT_ALL_QUERY);

			companies = this.companyMapper.companyDTOFromResultSet(result);
			
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
