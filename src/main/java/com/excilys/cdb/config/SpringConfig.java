package com.excilys.cdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.excilys.cdb.model.dao.DataSource;

@Configuration
@ComponentScan({"com.excilys.cdb.model.dao","com.excilys.cdb.model.mapper","com.excilys.cdb.model.validator",
	"com.excilys.cdb.service","com.excilys.cdb.servlet","com.excilys.cdb.controller","com.excilys.cdb.ui"})

public class SpringConfig  extends AbstractContextLoaderInitializer {

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringConfig.class);
		return context;
	}
	
	@Bean
	public DataSource getDataSource() {
		return new DataSource();
	}
}
