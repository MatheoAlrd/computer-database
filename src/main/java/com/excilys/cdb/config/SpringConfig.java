package com.excilys.cdb.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan({"com.excilys.cdb.model.dao","com.excilys.cdb.model.mapper","com.excilys.cdb.model.validator",
	"com.excilys.cdb.service","com.excilys.cdb.servlet","com.excilys.cdb.controller","com.excilys.cdb.ui"})

public class SpringConfig  extends AbstractContextLoaderInitializer {
	
	private static final String PROPERTIES = "datasource.properties";

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringConfig.class);
		return context;
	}
	
	@Bean
	public DataSource getDataSource() {
		return new HikariDataSource(new HikariConfig(PROPERTIES));
	}
}
