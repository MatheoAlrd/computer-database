
package com.excilys.cdb.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.mapper.CompanyMapper;

public class CompanyDAO extends DAO<CompanyDTO> {

	private static final String SELECT_BY_ID_QUERY = "SELECT id, name FROM company WHERE id = ?";
	private static final String SELECT_ALL_QUERY = "SELECT id, name FROM company";
	
	private static final String CREATE_QUERY = "INSERT INTO company (name) VALUES (?)";
	
	private static final String DELETE_COMPUTERS_BY_COMPANY_QUERY = "DELETE FROM computer WHERE company_id = ?";
	private final static String DELETE_COMPANY_QUERY = "DELETE FROM company WHERE id = ?";
	
	private static final String SET_AUTOCOMMIT = "SET autocommit = 0";
	private static final String ROLLBACK = "ROLLBACK";
	private static final String COMMIT = "COMMIT";

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
			logger.error("Couldn't find the company by its id "+e.getMessage());
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
			logger.error("Couldn't find all the companies "+e.getMessage());
		} finally {
			this.closeConnection();
		}
		return companies;
	}
	
	public void create(CompanyDTO c) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,c.getName());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Couldn't create the company "+e.getMessage());
		} finally {
			this.closeConnection();
		}
	}
	
	public void delete(int id) {
		try {
			this.openConnection();
			
			this.getConnection().createStatement().executeQuery(SET_AUTOCOMMIT);

			PreparedStatement ps = this.getConnection()
					.prepareStatement(DELETE_COMPUTERS_BY_COMPANY_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			ps = this.getConnection()
					.prepareStatement(DELETE_COMPANY_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			this.getConnection().createStatement().executeQuery(COMMIT);

			
		} catch(Exception e1) {
			try {
				logger.warn("Something didn't work in the transaction, start of the rollback" + e1.getMessage());
				this.getConnection().createStatement().executeQuery(ROLLBACK);

			} catch (SQLException e2) {
				logger.error("Rollback didn't work " + e2.getMessage());
			}
		}
	}
	
	public static CompanyDAO getInstance() {
		if (instance == null) {
			instance = new CompanyDAO();
		}		
		return instance;
	}

}
