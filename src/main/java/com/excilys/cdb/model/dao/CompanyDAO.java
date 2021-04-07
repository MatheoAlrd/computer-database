
package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.mapper.CompanyMapper;

@Component
public class CompanyDAO {

	private static final String SELECT_BY_ID_QUERY = "SELECT id, name FROM company WHERE id = :id";
	private static final String SELECT_ALL_QUERY = "SELECT id, name FROM company";
	
	private static final String CREATE_QUERY = "INSERT INTO company (name) VALUES (?)";
	
	private static final String DELETE_COMPUTERS_BY_COMPANY_QUERY = "DELETE FROM computer WHERE company_id = ?";
	private final static String DELETE_COMPANY_QUERY = "DELETE FROM company WHERE id = ?";
	
	private static final String SET_AUTOCOMMIT = "SET autocommit = 0";
	private static final String ROLLBACK = "ROLLBACK";
	private static final String COMMIT = "COMMIT";

	protected static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	private DataSource datasource;
	private CompanyRowMapper companyRowMapper;
	
	public CompanyDAO(CompanyRowMapper companyRowMapper, DataSource datasource) {
		super();
		this.companyRowMapper = companyRowMapper;
		this.datasource = datasource;
	}
	
	public List<CompanyDTO> find(int id){
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id, Types.INTEGER);
	
		return new NamedParameterJdbcTemplate(datasource).query(SELECT_ALL_QUERY, params, companyRowMapper);
	}
	
	public List<CompanyDTO> findAll() {			
		return new JdbcTemplate(datasource).query(SELECT_BY_ID_QUERY, companyRowMapper);
	}
	
	public void create(CompanyDTO c) {
		try (Connection con = DataSource2.getConnection()){
			PreparedStatement ps = con.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,c.getName());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Couldn't create the company "+e.getMessage());
		}
	}
	
	public void delete(int id) {
		try (Connection con = DataSource2.getConnection()){
			
			con.createStatement().executeQuery(SET_AUTOCOMMIT);

			PreparedStatement ps = con.prepareStatement(DELETE_COMPUTERS_BY_COMPANY_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			ps = con.prepareStatement(DELETE_COMPANY_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			con.createStatement().executeQuery(COMMIT);			
		} catch(Exception e1) {
			try {
				logger.warn("Something didn't work in the transaction, start of the rollback" + e1.getMessage());
				DataSource2.getConnection().createStatement().executeQuery(ROLLBACK);

			} catch (SQLException e2) {
				logger.error("Rollback didn't work " + e2.getMessage());
			}
		}
	}
}
