
package com.excilys.cdb.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.mapper.ComputerMapper;

public class ComputerDAO extends DAO<Computer> {

	private static final String CREATE_QUERY = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private static final String DELETE_QUERY = "DELETE FROM computer WHERE id = ?";
	private static final String UPDATE_QUERY = "UPDATE computer SET name = ?,  introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private static final String SELECT_BY_ID_QUERY = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE computer.id = ? ";
	private static final String SELECT_BY_NAME_QUERY = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE computer.name LIKE ? ";
	private static final String SELECT_ALL_QUERY = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id";


	private static ComputerDAO instance;
	private ComputerMapper computerMapper = new ComputerMapper();

	private ComputerDAO() {
		super();
		logger = LoggerFactory.getLogger(ComputerDAO.class);
	}

	public void create(Computer c) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);

			ps = computerMapper.preparedStatementFromComputer(ps, c);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}

	public void delete(int id) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(DELETE_QUERY, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}

	public void update(int id, Computer c) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS);

			ps = computerMapper.preparedStatementFromComputer(ps, c);
			ps.setInt(5, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}

	public List<Computer> find(int id) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_BY_ID_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();
			return computerMapper.computerFromResultSet(result);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public List<Computer> find(String name) {

		List<Computer> computers = new ArrayList<Computer>();
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_BY_NAME_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, "%"+name+"%");

			ResultSet result = ps.executeQuery();

			return computerMapper.computerFromResultSet(result);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return computers;
	}

	public List<Computer> findAll() {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_ALL_QUERY, Statement.RETURN_GENERATED_KEYS);

			ResultSet result = ps.executeQuery();

			return computerMapper.computerFromResultSet(result);

		} catch (SQLException e) {
			System.out.println("je catch ici");
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return new ArrayList<Computer>();
	}

	public List<Company> findCompany(int id) throws SQLException {
		return CompanyDAO.getInstance().find(id);
	}

	public static ComputerDAO getInstance() {
		if (instance == null) {
			instance = new ComputerDAO();
		}		
		return instance;
	}

}
