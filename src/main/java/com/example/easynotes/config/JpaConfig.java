package com.example.easynotes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.easynotes.repository")
@PropertySource("classpath:application.yml")
@EnableTransactionManagement
public class JpaConfig {

    @Autowired
    private Environment env;

    @Autowired
    private TestConfig config; //= new TestConfig();


    @Bean
    public DataSource dataSource() {
        config.testConfigRun();

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.user"));
        dataSource.setPassword(env.getProperty("spring.datasource.pass"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.example.easynotes.model" });
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("spring.hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("spring.hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("spring.hibernate.cache.use_second_level_cache"));
        hibernateProperties.setProperty("hibernate.cache.region.factory_class", env.getProperty("spring.hibernate.cache.region.factory_class"));
        hibernateProperties.setProperty("hibernate.cache.use_query_cache", env.getProperty("spring.hibernate.cache.use_query_cache"));

        return hibernateProperties;
    }
}