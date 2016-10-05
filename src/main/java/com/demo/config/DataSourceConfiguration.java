package com.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataSourceConfiguration {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("system");
		dataSource.setPassword("password");
		return dataSource;
	}

	 @Bean
	 public EntityManagerFactory entityManagerFactory() {
	 HibernateJpaVendorAdapter vendorAdapter = new
	 HibernateJpaVendorAdapter();
	 vendorAdapter.setGenerateDdl(true);
	 LocalContainerEntityManagerFactoryBean factory = new
	 LocalContainerEntityManagerFactoryBean();
	 factory.setJpaVendorAdapter(vendorAdapter);
	 factory.setPackagesToScan("com.demo");
	 factory.setDataSource(dataSource());
	 factory.afterPropertiesSet();
	 return factory.getObject();
	 }

	// @Bean
	// public PlatformTransactionManager transactionManager() {
	// JpaTransactionManager txManager = new JpaTransactionManager();
	// txManager.setEntityManagerFactory(entityManagerFactory());
	// return txManager;
	// }

}