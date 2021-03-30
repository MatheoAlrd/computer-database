
package com.excilys.cdb.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

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
	
	private static final String SELECT_COUNT_ALL_QUERY = "SELECT COUNT(id) FROM computer";
	private static final String SELECT_COUNT_BY_NAME_QUERY = "SELECT COUNT(id) FROM computer WHERE computer.name LIKE ?";
	
	private static final String LIMIT = "LIMIT ?,? ";
	private static final String ORDER_BY = "ORDER BY ";
	private static final String ASC = " ASC ";
	private static final String DESC = " DESC ";

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
			logger.error("Couldn't create the computer "+e.getMessage());
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
			logger.error("Couldn't delete the computer "+e.getMessage());
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
			logger.error("Couldn't update the computer "+e.getMessage());
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
			logger.error("Couldn't find the computer by its id "+e.getMessage());
		} finally {
			this.closeConnection();
		}
		return new ArrayList<ComputerDTO>();
	}

	public List<ComputerDTO> find(String name) {

		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_BY_NAME_QUERY,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, "%"+name+"%");

			ResultSet result = ps.executeQuery();

			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error("Couldn't find the computer by its name "+e.getMessage());
		} finally {
			this.closeConnection();
		}
		return new ArrayList<ComputerDTO>();
	}

	public List<ComputerDTO> findAll() {
		try {
			this.openConnection();

			ResultSet result = this.getConnection().createStatement().executeQuery(SELECT_ALL_QUERY);

			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error("Couldn't find all computers "+e.getMessage());
		} finally {
			this.closeConnection();
		}
		return new ArrayList<ComputerDTO>();
	}
	
	public List<ComputerDTO> findPage(String name, int pageSize, int offset) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_BY_NAME_QUERY + LIMIT,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, "%"+name+"%");
			ps.setInt(2, offset);
			ps.setInt(3, pageSize);

			ResultSet result = ps.executeQuery();

			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error("Couldn't find all the computers by their name in the page "+e.getMessage());
		} finally {
			this.closeConnection();
		}
		return new ArrayList<ComputerDTO>();
	}
	
	public List<ComputerDTO> findAllPage(int pageSize, int offset) {
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection()
					.prepareStatement(SELECT_ALL_QUERY + LIMIT,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, offset);
			ps.setInt(2, pageSize);

			ResultSet result = ps.executeQuery();

			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error("Couldn't find all the computers in the page "+e.getMessage());
		} finally {
			this.closeConnection();
		}
		return new ArrayList<ComputerDTO>();
	}

	public List<ComputerDTO> findPageOrderBy(String name, int pageSize, int offset, String sort, boolean asc) {
		try {
			this.openConnection();
			String query = SELECT_BY_NAME_QUERY + ORDER_BY + "computer." + sort;
			if(asc) {
				query+=ASC;
			} else {
				query+=DESC;
			}
			query+=LIMIT;
			PreparedStatement ps = this.getConnection()
					.prepareStatement(query,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, "%"+name+"%");
			ps.setInt(2, offset);
			ps.setInt(3, pageSize);

			ResultSet result = ps.executeQuery();

			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error("Couldn't find all the computers by their name in the page "+e.getMessage());
		} finally {
			this.closeConnection();
		}
		return new ArrayList<ComputerDTO>();
	}
	
	public List<ComputerDTO> findAllPageOrderBy(int pageSize, int offset, String sort, boolean asc) {
		try {
			this.openConnection();			
			String query = SELECT_ALL_QUERY + ORDER_BY + "computer." + sort;
			if(asc) {
				query+=ASC;
			} else {
				query+=DESC;
			}
			query+=LIMIT;
			System.out.println(query+" "+sort+" "+offset+" "+pageSize);
			PreparedStatement ps = this.getConnection()
					.prepareStatement(query,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setInt(2, offset);
			ps.setInt(3, pageSize);

			ResultSet result = ps.executeQuery();

			return computerMapper.computersFromResultSet(result);

		} catch (SQLException e) {
			logger.error("Couldn't find all the computers in the page "+e.getMessage());
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
			logger.error("Couldn't count all the computer "+e.getMessage());
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
			logger.error("Couldn't count all the computer by their names "+e.getMessage());
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
