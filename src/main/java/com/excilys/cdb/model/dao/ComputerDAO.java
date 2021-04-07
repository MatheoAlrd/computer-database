
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
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.mapper.ComputerMapper;

@Component
public class ComputerDAO {

	private static final String CREATE_QUERY = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private static final String DELETE_QUERY = "DELETE FROM computer WHERE id = ?";
	private static final String UPDATE_QUERY = "UPDATE computer SET name = ?,  introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

	private static final String SELECT_BY_ID_QUERY = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE computer.id = ? ";
	private static final String SELECT_BY_NAME_QUERY = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE computer.name LIKE :name ";
	private static final String SELECT_ALL_QUERY = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id";

	private static final String SELECT_COUNT_ALL_QUERY = "SELECT COUNT(id) FROM computer";
	private static final String SELECT_COUNT_BY_NAME_QUERY = "SELECT COUNT(id) FROM computer WHERE computer.name LIKE ?";

	private static final String LIMIT = "LIMIT :offset,:limit ";
	private static final String ORDER_BY = "ORDER BY ";
	private static final String ASC = " ASC ";
	private static final String DESC = " DESC ";

	protected static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	private DataSource datasource;
	private CompanyDAO companyDAO;
	private ComputerMapper computerMapper;

	private ComputerDAO(CompanyDAO companyDAO, ComputerMapper computerMapper) {
		super();
		this.companyDAO = companyDAO;
		this.computerMapper = computerMapper;
	}

	public void create(ComputerDTO c) {
		try (Connection con = DataSource2.getConnection()) {
			PreparedStatement ps = con.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);

			ps = computerMapper.preparedStatementFromComputer(ps, c);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Couldn't create the computer "+e.getMessage());
		}
	}

	public void delete(int id) {
		try (Connection con = DataSource2.getConnection()){
			PreparedStatement ps = con.prepareStatement(DELETE_QUERY, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Couldn't delete the computer "+e.getMessage());
		}
	}

	public void update(int id, ComputerDTO c) {
		try (Connection con = DataSource2.getConnection()){
			PreparedStatement ps = con.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS);

			ps = computerMapper.preparedStatementFromComputer(ps, c);
			ps.setInt(5, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Couldn't update the computer "+e.getMessage());
		}
	}

	public List<ComputerDTO> find(int id) {
		try (Connection con = DataSource2.getConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY,
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();
			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error("Couldn't find the computer by its id "+e.getMessage());
		}
		return new ArrayList<ComputerDTO>();
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

		params.addValue("offset", page.getPageSize()*(page.getCurrentPage()-1), Types.INTEGER);
		params.addValue("limit", page.getPageSize(), Types.INTEGER);

		return new JdbcTemplate(datasource).query(query, new ComputerRowMapper());
	}

	public List<ComputerDTO> findAllPageOrderBy(Page<ComputerDTO> page) {

		String query = SELECT_ALL_QUERY + ORDER_BY + "computer." + page.getSort();
		if(page.isAsc()) {
			query+=ASC;
		} else {
			query+=DESC;
		}
		query+=LIMIT;

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("offset", page.getPageSize()*(page.getCurrentPage()-1), Types.INTEGER);
		params.addValue("limit", page.getPageSize(), Types.INTEGER);

		return new JdbcTemplate(datasource).query(query, new ComputerRowMapper());
	}

	public List<CompanyDTO> findCompany(int id) throws SQLException {
		return this.companyDAO.find(id);
	}

	public int count() {
		try (Connection con = DataSource2.getConnection()){
			ResultSet result = con.createStatement().executeQuery(SELECT_COUNT_ALL_QUERY);
			if(result.next()) {
				return result.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("Couldn't count all the computer "+e.getMessage());
		}
		return 0; 
	}

	public int count(String name) {
		try (Connection con = DataSource2.getConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_COUNT_BY_NAME_QUERY,
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, "%"+name+"%");

			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return result.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("Couldn't count all the computer by their names "+e.getMessage());
		}
		return 0;
	}
}
