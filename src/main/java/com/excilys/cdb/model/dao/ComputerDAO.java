
package com.excilys.cdb.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
	private static final String UPDATE_INTRODUCED_QUERY = "UPDATE computer SET introduced = ? WHERE id = ?";
	private static final String UPDATE_DISCONTINUED_QUERY = "UPDATE computer SET discontinued = ? WHERE id = ?";
	private static final String UPDATE_COMPANY_ID_QUERY = "UPDATE computer SET company_id = ? WHERE id = ?";
	private static final String SELECT_BY_ID_QUERY = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?";
	private static final String SELECT_BY_NAME_QUERY = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ?";
	private static final String SELECT_ALL_QUERY = "SELECT id, name, introduced, discontinued, company_id FROM computer";

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

	public void updateIntroduced(int id, LocalDate introduced) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(UPDATE_INTRODUCED_QUERY, Statement.RETURN_GENERATED_KEYS);

			if (introduced != null) {
				ps.setDate(1, Date.valueOf(introduced));
			} else {
				ps.setDate(1, null);
			}

			ps.setInt(2, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}

	public void updateDiscontinued(int id, LocalDate discontinued) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(UPDATE_DISCONTINUED_QUERY, Statement.RETURN_GENERATED_KEYS);

			if (discontinued != null) {
				ps.setDate(1, Date.valueOf(discontinued));
			} else {
				ps.setDate(1, null);
			}

			ps.setInt(2, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
	}

	public void updateCompany(int id, int companyId) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(UPDATE_COMPANY_ID_QUERY, Statement.RETURN_GENERATED_KEYS);

			if (companyId != -1) {
				ps.setInt(1, companyId);
			} else {
				ps.setObject(1, null);
			}

			ps.setInt(2, id);
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

	public Computer find(int id) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_BY_ID_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (result.first()) {
				return computerMapper.computerFromResultSet(result);

			} else {
				System.out.println("This computer does not exist");
			}
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

			while (result.next()) {											
				computers.add(computerMapper.computerFromResultSet(result));
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return computers;
	}

	public List<Computer> findAll() {

		List<Computer> computers = new ArrayList<Computer>();
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_ALL_QUERY, Statement.RETURN_GENERATED_KEYS);

			ResultSet result = ps.executeQuery();

			while (result.next()) {											
				computers.add(computerMapper.computerFromResultSet(result));
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			this.closeConnection();
		}
		return computers;
	}

	public Company findCompany(int id) throws SQLException {
		return CompanyDAO.getInstance().find(id);
	}

	public static ComputerDAO getInstance() {
		if (instance == null) {
			instance = new ComputerDAO();
		}		
		return instance;
	}

}
