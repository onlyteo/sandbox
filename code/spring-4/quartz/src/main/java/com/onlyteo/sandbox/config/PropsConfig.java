package com.onlyteo.sandbox.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropsConfig {

    public static final String DATABASE_PROPERTIES = "DatabaseProperties";
    public static final String DATABASE_PROPERTIES_FILE = "properties/database.properties";
    public static final String DATABASE_URL = "database.url";
    public static final String DATABASE_USERNAME = "database.username";
    public static final String DATABASE_PASSWORD = "database.password";
    public static final String DATABASE_MAX_WAIT = "database.max.wait";
    public static final String DATABASE_DIALECT_CLASS = "database.dialect.class";
    public static final String DATABASE_DRIVER_CLASS = "database.driver.class";

    public final static String BATCH_PROPERTIES = "BatchProperties";
    public final static String BATCH_PROPERTIES_FILE = "properties/batch.properties";
    public final static String BATCH_JOBS_SHOULD_RUN = "batch.jobs.should.run";
    public final static String BATCH_CRON_SCHEDULE = "batch.cron.schedule";
    public final static String BATCH_JOB_DESCRIPTION = "batch.job.description";
    public static final String BATCH_WORKER_JOB_DESCRIPTION = "batch.worker.job.description";
    public static final String BATCH_INITIAL_START_DATE = "batch.initial.start.date";

    public final static String QUARTZ_PROPERTIES = "QuartzProperties";
    public final static String QUARTZ_PROPERTIES_FILE = "properties/quartz.properties";

    @Bean(name = DATABASE_PROPERTIES)
    public PropertiesFactoryBean databaseProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource(DATABASE_PROPERTIES_FILE));
        return bean;
    }

    @Bean(name = BATCH_PROPERTIES)
    public PropertiesFactoryBean batchProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource(BATCH_PROPERTIES_FILE));
        return bean;
    }

    @Bean(name = QUARTZ_PROPERTIES)
    public PropertiesFactoryBean quartzProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource(QUARTZ_PROPERTIES_FILE));
        return bean;
    }
}
