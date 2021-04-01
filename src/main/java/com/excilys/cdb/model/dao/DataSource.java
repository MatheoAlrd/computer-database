package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DataSource {

	private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
    	config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    	config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db");
        config.setUsername("admincdb");
        config.setPassword("qwerty1234");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
