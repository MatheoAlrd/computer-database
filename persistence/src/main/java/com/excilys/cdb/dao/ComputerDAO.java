
package com.excilys.cdb.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Page;
import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerRowMapper;

@Component
public class ComputerDAO {

	private static final String CREATE_QUERY = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (:name,:introduced,:discontinued,:companyId)";
	private static final String DELETE_QUERY = "DELETE FROM computer WHERE id = :id";
	private static final String UPDATE_QUERY = "UPDATE computer SET name = :name,  introduced = :introduced, discontinued = :discontinued, company_id = :companyId WHERE id = :id";

	private static final String SELECT_BY_ID_QUERY = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE computer.id = :id ";
	private static final String SELECT_BY_NAME_QUERY = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE computer.name LIKE :name ";

	private static final String SELECT_COUNT_BY_NAME_QUERY = "SELECT COUNT(id) FROM computer WHERE computer.name LIKE :name";

	private static final String LIMIT = "LIMIT :limit OFFSET :offset";
	private static final String ORDER_BY = "ORDER BY ";
	private static final String ASC = " ASC ";
	private static final String DESC = " DESC ";

	protected static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	private DataSource datasource;
	private CompanyDAO companyDAO;
	private ComputerRowMapper computerRowMapper;

	private ComputerDAO(CompanyDAO companyDAO, ComputerRowMapper computerRowMapper, DataSource datasource) {
		super();
		this.companyDAO = companyDAO;
		this.computerRowMapper = computerRowMapper;
		this.datasource = datasource;
	}

	public void create(ComputerDTO c) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", c.getName(), Types.VARCHAR);
		params.addValue("introduced", c.getIntroduced(), Types.DATE);
		params.addValue("discontinued", c.getDiscontinued(), Types.DATE);
		params.addValue("companyId", c.getCompanyId(), Types.INTEGER);

		new NamedParameterJdbcTemplate(datasource).update(CREATE_QUERY, params);
	}

	public void delete(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id, Types.INTEGER);

		new NamedParameterJdbcTemplate(datasource).update(DELETE_QUERY, params);
	}

	public void update(int id, ComputerDTO c) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", c.getName(), Types.VARCHAR);
		params.addValue("introduced", c.getIntroduced(), Types.DATE);
		params.addValue("discontinued", c.getDiscontinued(), Types.DATE);
		params.addValue("companyId", c.getCompanyId(), Types.INTEGER);
		params.addValue("id", id, Types.INTEGER);

		new NamedParameterJdbcTemplate(datasource).update(UPDATE_QUERY, params);
	}

	public List<ComputerDTO> find(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id, Types.INTEGER);

		return new NamedParameterJdbcTemplate(datasource).query(SELECT_BY_ID_QUERY, params, computerRowMapper);
	}

	public List<ComputerDTO> findPageOrderBy(String name, Page<ComputerDTO> page) {

		String query = SELECT_BY_NAME_QUERY + ORDER_BY + "computer." + page.getSort();
		if(page.isAsc()) {
			query+=ASC;
		} else {
			query+=DESC;
		}
		query+=LIMIT;
				
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "%"+name+"%", Types.VARCHAR);
		params.addValue("offset", page.offset(), Types.INTEGER);
		params.addValue("limit", page.getPageSize(), Types.INTEGER);

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(datasource);

		List<ComputerDTO> listComputerDTO = jdbcTemplate.query(query, params, this.computerRowMapper);

		return listComputerDTO;
	}

	public List<CompanyDTO> findCompany(int id) throws SQLException {
		return this.companyDAO.find(id);
	}

	public int count(String name) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "%"+name+"%", Types.VARCHAR);

		return new NamedParameterJdbcTemplate(datasource).queryForObject(SELECT_COUNT_BY_NAME_QUERY, params, Integer.class);
	}
}
