package com.onlyteo.sandbox.config;

import com.onlyteo.sandbox.batch.AutowiringSpringBeanJobFactory;
import com.onlyteo.sandbox.batch.ConditionalSchedulerFactoryBean;
import com.onlyteo.sandbox.control.BatchJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Configuration
@EnableScheduling
public class QuartzConfig {

    public static final String BATCH_SCHEDULER = "BatchScheduler";
    public static final String BATCH_JOB_SCHEDULER = "BatchJobScheduler";
    public static final String BATCH_JOB_GROUP = "BatchJobGroup";
    public static final String BATCH_JOB = "BatchJob";
    public static final String BATCH_JOB_TRIGGER = "BatchJobTrigger";
    public static final String BATCH_WORKER_JOB_GROUP = "BatchWorkerJobGroup";
    public static final String BATCH_WORKER_JOB = "BatchWorkerJob";
    public static final String BATCH_WORKER_JOB_TRIGGER = "BatchWorkerJobTrigger";

    @Autowired
    @Qualifier(PropsConfig.BATCH_PROPERTIES)
    private Properties batchProperties;

    @Bean
    public JobFactory createJobFactory() {
        return new AutowiringSpringBeanJobFactory();
    }

    @Bean(name = BATCH_JOB)
    public JobDetailFactoryBean createJob() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(BatchJob.class);
        factoryBean.setDescription(batchProperties.getProperty(PropsConfig.BATCH_JOB_DESCRIPTION));
        factoryBean.setDurability(TRUE);
        factoryBean.setGroup(BATCH_JOB_GROUP);
        factoryBean.setName(BATCH_JOB);
        return factoryBean;
    }

    @Bean(name = BATCH_JOB_TRIGGER)
    @Autowired
    public CronTriggerFactoryBean createJobTrigger(@Qualifier(BATCH_JOB) final JobDetail jobDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setGroup(BATCH_JOB_GROUP);
        factoryBean.setName(BATCH_JOB_TRIGGER);
        factoryBean.setCronExpression(batchProperties.getProperty(PropsConfig.BATCH_CRON_SCHEDULE));
        return factoryBean;
    }

    @Bean(name = BATCH_JOB_SCHEDULER)
    @Autowired
    @DependsOn(DatabaseConfig.LIQUIBASE_BEAN)
    public SchedulerFactoryBean createJobSchedule(final ApplicationContext context, @Qualifier(DatabaseConfig.BATCH_DATASOURCE) final DataSource dataSource, @Qualifier(DatabaseConfig.DATABASE_TX_MANAGER) final PlatformTransactionManager transactionManager, final JobFactory jobFactory, @Qualifier(BATCH_JOB) final JobDetail jobDetail, @Qualifier(BATCH_JOB_TRIGGER) final Trigger jobTrigger, @Qualifier(PropsConfig.QUARTZ_PROPERTIES) final Properties quartzProperties) {
        ConditionalSchedulerFactoryBean factoryBean = new ConditionalSchedulerFactoryBean(shouldStartJobs());
        factoryBean.setApplicationContext(context);
        factoryBean.setTransactionManager(transactionManager);
        factoryBean.setDataSource(dataSource);
        factoryBean.setJobFactory(jobFactory);
        factoryBean.setJobDetails(jobDetail);
        factoryBean.setTriggers(jobTrigger);
        factoryBean.setSchedulerName(BATCH_SCHEDULER);
        factoryBean.setAutoStartup(TRUE);
        factoryBean.setWaitForJobsToCompleteOnShutdown(TRUE);
        factoryBean.setOverwriteExistingJobs(TRUE);
        factoryBean.setQuartzProperties(quartzProperties);
        return factoryBean;
    }

    public boolean shouldStartJobs() {
        String value = batchProperties.getProperty(PropsConfig.BATCH_JOBS_SHOULD_RUN, FALSE.toString());
        return Boolean.parseBoolean(value);
    }
}
