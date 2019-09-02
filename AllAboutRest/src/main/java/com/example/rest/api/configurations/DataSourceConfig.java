package com.example.rest.api.configurations;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/* Example configuration to setup the 
 * - DataSource
 * - EntityManager
 * - TransactionManager
 * 
 * Note: if we want to configure multiple databases then,
 *       add one more configuration file and change the 
 *         - DataSource (new DB details and new bean name)
 *         - EntityManager (new package scan path and new bean name)
 *         - TransactionManager (configure new entity manager)
 *  Also, note that we have to make one configuration to return @primary beans
 * 
 * 
 */

@Configuration
@EnableJpaRepositories(//
		basePackages = "com.example.rest.api.repository", //
		entityManagerFactoryRef = "entityManager", //
		transactionManagerRef = "transactionManager")
public class DataSourceConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.example.rest.api.model" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		/*
		 * NOTE: Following suffix is required ;DB_CLOSE_DELAY=-1
		 * 
		 * If you don't provide this then the DB connection will immediately close and
		 * table creation for your entities won't take place
		 * 
		 * 
		 */
		dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("SA");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManager().getObject());
		return transactionManager;
	}

}