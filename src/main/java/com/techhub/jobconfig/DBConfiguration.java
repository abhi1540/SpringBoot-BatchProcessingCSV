package com.techhub.jobconfig;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfiguration {
	
	@Bean(name="primaryDS")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource primaryDS() {
		return DataSourceBuilder.create().build();
		
	}

}
