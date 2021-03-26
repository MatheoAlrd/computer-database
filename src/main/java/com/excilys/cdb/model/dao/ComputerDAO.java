
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
import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.dto.ComputerDTO;
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
	private static final String SELECT_BY_NAME_LIMIT_QUERY = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE computer.name LIKE ? "
			+ "LIMIT ?,?";
	private static final String SELECT_ALL_LIMIT_QUERY = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id "
			+ "LIMIT ?,?";
	private static final String SELECT_COUNT_ALL_QUERY = "SELECT COUNT(id) FROM computer";
	private static final String SELECT_COUNT_BY_NAME_QUERY = "SELECT COUNT(id) FROM computer WHERE computer.name LIKE ?";

	private static ComputerDAO instance;
	private ComputerMapper computerMapper = new ComputerMapper();

	private ComputerDAO() {
		super();
		logger = LoggerFactory.getLogger(ComputerDAO.class);
	}

	public void create(ComputerDTO c) {
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

	public void update(int id, ComputerDTO c) {
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

	public List<ComputerDTO> find(int id) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_BY_ID_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();
			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public List<ComputerDTO> find(String name) {

		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_BY_NAME_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, "%"+name+"%");

			ResultSet result = ps.executeQuery();

			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return computers;
	}

	public List<ComputerDTO> findAll() {
		try {
			this.openConnection();

			ResultSet result = this.getConnection().createStatement().executeQuery(SELECT_ALL_QUERY);

			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return new ArrayList<ComputerDTO>();
	}
	
	public List<ComputerDTO> findPage(String name, int pageSize, int offset) {

		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_BY_NAME_LIMIT_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, "%"+name+"%");
			ps.setInt(2, offset);
			ps.setInt(3, pageSize);

			ResultSet result = ps.executeQuery();

			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return computers;
	}
	
	public List<ComputerDTO> findAllPage(int pageSize, int offset) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_ALL_LIMIT_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, offset);
			ps.setInt(2, pageSize);

			ResultSet result = ps.executeQuery();

			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return new ArrayList<ComputerDTO>();
	}

	public List<CompanyDTO> findCompany(int id) throws SQLException {
		return CompanyDAO.getInstance().find(id);
	}
	
	public int count() {
		try {
			this.openConnection();

			ResultSet result = this.getConnection().createStatement().executeQuery(SELECT_COUNT_ALL_QUERY);
			if(result.next()) {
				return result.getInt(1);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return 0; 
	}
	
	public int count(String name) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_COUNT_BY_NAME_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, "%"+name+"%");

			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return result.getInt(1);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return 0;
	}

	public static ComputerDAO getInstance() {
		if (instance == null) {
			instance = new ComputerDAO();
		}		
		return instance;
	}

}
