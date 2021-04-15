
package com.excilys.cdb.dao;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.mapper.CompanyRowMapper;

@Component
public class CompanyDAO {

	private static final String SELECT_BY_ID_QUERY = "SELECT id, name FROM company WHERE id = :id";
	private static final String SELECT_ALL_QUERY = "SELECT id, name FROM company";

	private static final String CREATE_QUERY = "INSERT INTO company (name) VALUES (:name)";

	private static final String DELETE_COMPUTERS_BY_COMPANY_QUERY = "DELETE FROM computer WHERE company_id = :id";
	private final static String DELETE_COMPANY_QUERY = "DELETE FROM company WHERE id = :id";


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

		return new NamedParameterJdbcTemplate(datasource).query(SELECT_BY_ID_QUERY, params, companyRowMapper);
	}

	public List<CompanyDTO> findAll() {			
		return new JdbcTemplate(datasource).query(SELECT_ALL_QUERY, companyRowMapper);
	}

	public void create(CompanyDTO c) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", c.getName(), Types.VARCHAR);

		new NamedParameterJdbcTemplate(datasource).update(CREATE_QUERY, params);
	}

	@Transactional
	public void delete(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id, Types.VARCHAR);

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(datasource);
		jdbcTemplate.update(DELETE_COMPUTERS_BY_COMPANY_QUERY, params);
		jdbcTemplate.update(DELETE_COMPANY_QUERY, params);
	}
}
