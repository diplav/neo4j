package com.dbit.neo4j.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//dbTransactionManager  dbEntityManager
@Configuration
@EnableJpaRepositories(basePackages = {"com.dbit.neo4j.mysql"},
        entityManagerFactoryRef = "dbEntityManager",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class DatabaseConfiguration {
	
	 @Autowired
	    private Environment env;
	    @Bean
	    @Primary
	    public LocalContainerEntityManagerFactoryBean dbEntityManager() {
	        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	        em.setDataSource(dbDatasource());
	        em.setPackagesToScan(new String[]{"com.dbit.neo4j.mysql"});
	        em.setPersistenceUnitName("dbEntityManager");
	        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	        em.setJpaVendorAdapter(vendorAdapter);
	        
	        HashMap<String, Object> properties = new HashMap<>();
	        
	        properties.put("hibernate.dialect",env.getProperty("spring.jpa.properties.hibernate.dialect"));
	        properties.put("hibernate.show-sql",env.getProperty("spring.jpa.show-sql"));
	        properties.put("hibernate.generate-ddl",env.getProperty("spring.jpa.generate-ddl"));
	        
	        
	        properties.put("hibernate.hbm2ddl.auto", "update"); //auto creation of tables
	   
	        
	        em.setJpaPropertyMap(properties);
	        return em;
	    }
	    @Primary
	    @Bean
	    public DataSource dbDatasource() {
	        DriverManagerDataSource dataSource
	                = new DriverManagerDataSource();
	        dataSource.setDriverClassName(
	                env.getProperty("spring.datasource.driverClassName"));
	        dataSource.setUrl(env.getProperty("spring.datasource.url"));
	        dataSource.setUsername(env.getProperty("spring.datasource.username"));
	        dataSource.setPassword(env.getProperty("spring.datasource.password"));
	        return dataSource;
	    }
	    @Primary
	    @Bean(name="transactionManager")
	    public PlatformTransactionManager TransactionManager() {
	        JpaTransactionManager transactionManager
	                = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(
	                dbEntityManager().getObject());
	        return transactionManager;
	    }

}





