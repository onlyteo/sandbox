package com.onlyteo.sandbox.config;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    public static final String BATCH_DATASOURCE = "BatchDatasource";
    public static final String LIQUIBASE_BEAN = "LiquibaseBean";
    public static final String LIQUIBASE_CHANGELOG = "liquibase.changelog";
    private static final String LIQUIBASE_CONTEXT = "liquibase.context";
    public static final String DATABASE_EM_FACTORY = "DatabaseEntityManagerFactory";
    public static final String DATABASE_PERSISTANCE_UNIT = "com.onlyteo.sandbox.spring-4-quartz";
    public static final String DATABASE_TX_MANAGER = "TransactionManager";

    @Bean(name = BATCH_DATASOURCE)
    public DataSource getDataSource(@Qualifier(PropsConfig.DATABASE_PROPERTIES) final Properties properties) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getProperty(PropsConfig.DATABASE_DRIVER_CLASS));
        dataSource.setUrl(properties.getProperty(PropsConfig.DATABASE_URL));
        dataSource.setUsername(properties.getProperty(PropsConfig.DATABASE_USERNAME));
        String password = properties.getProperty(PropsConfig.DATABASE_PASSWORD);
        if (password != null && !password.isEmpty()) {
            dataSource.setPassword(password);
        }
        String maxWait = properties.getProperty(PropsConfig.DATABASE_MAX_WAIT);
        if (maxWait != null && !maxWait.isEmpty()) {
            dataSource.setMaxWait(Long.parseLong(maxWait));
        }
        return dataSource;
    }

    @Bean(name = LIQUIBASE_BEAN)
    @Autowired
    public SpringLiquibase getLiquibase(@Qualifier(BATCH_DATASOURCE) final DataSource dataSource, @Qualifier(PropsConfig.DATABASE_PROPERTIES) final Properties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getProperty(LIQUIBASE_CHANGELOG));
        liquibase.setContexts(properties.getProperty(LIQUIBASE_CONTEXT));
        return liquibase;
    }

    @Bean
    @Autowired
    public JpaVendorAdapter getJpaVendorAdapter(@Qualifier(PropsConfig.DATABASE_PROPERTIES) final Properties properties) {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform(properties.getProperty(PropsConfig.DATABASE_DIALECT_CLASS));
        jpaVendorAdapter.setShowSql(false);
        return jpaVendorAdapter;
    }

    @Bean(name = DATABASE_EM_FACTORY)
    @Autowired
    public LocalContainerEntityManagerFactoryBean getEntitiManagerFactory(final JpaVendorAdapter jpaVendorAdapter, @Qualifier(BATCH_DATASOURCE) final DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setDataSource(dataSource);
        factory.setPersistenceUnitName(DATABASE_PERSISTANCE_UNIT);
        return factory;
    }

    @Bean(name = DATABASE_TX_MANAGER)
    @Autowired
    public JpaTransactionManager getTransactionManager(@Qualifier(DATABASE_EM_FACTORY) final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
