package com.excilys.cdb.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.excilys.cdb.model.dao.DataSource;

@Configuration

public class SpringWebConfig implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringWebConfig.class);
		context.setServletContext(servletContext);
	}
	
	@Bean
	public DataSource getDataSource() {
		return new DataSource();
	}

}
