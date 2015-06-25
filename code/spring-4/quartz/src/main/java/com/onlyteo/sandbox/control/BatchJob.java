package com.onlyteo.sandbox.control;

import com.onlyteo.sandbox.config.PropsConfig;
import com.onlyteo.sandbox.config.QuartzConfig;
import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static java.lang.Boolean.TRUE;

@Component
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class BatchJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);

    @Autowired
    @Qualifier(PropsConfig.BATCH_PROPERTIES)
    private Properties properties;

    @Autowired
    @Qualifier(QuartzConfig.BATCH_JOB_SCHEDULER)
    private SchedulerFactoryBean schedulerFactoryBean;

    public BatchJob() {
        LOGGER.info("Batch job created");
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        long startMillis = System.currentTimeMillis();
        final JobKey jobKey = context.getJobDetail().getKey();
        LOGGER.info("Executing batch job '{}' for group '{}'", jobKey.getName(), jobKey.getGroup());
        for (int i = 1; i <= 5; i++) {
            createAndScheduleWorkerJob(context, String.format("%03d", i));
        }
        LOGGER.info("Completed batch job '{}' for group '{}' in {}", jobKey.getName(), jobKey.getGroup(), duration(startMillis));
    }

    private void createAndScheduleWorkerJob(JobExecutionContext context, String uniqueId) throws JobExecutionException {
        try {
            final JobKey jobKey = context.getJobDetail().getKey();
            if (schedulerFactoryBean != null) {
                Scheduler scheduler = schedulerFactoryBean.getScheduler();
                if (scheduler != null) {
                    final JobDetail workerJobDetail = createWorkerJobDetail(context, uniqueId);
                    final Trigger workerJobTrigger = createWorkerTrigger(uniqueId);
                    final Set<Trigger> workerJobTriggerSet = createWorkerTriggerSet(workerJobTrigger);
                    scheduler.scheduleJob(workerJobDetail, workerJobTriggerSet, TRUE);
                } else {
                    LOGGER.error("Job scheduler is null in batch job '{}' for group '{}'", jobKey.getName(), jobKey.getGroup());
                }
            } else {
                LOGGER.error("Job scheduler factory is null in batch job '{}' for group '{}'", jobKey.getName(), jobKey.getGroup());
            }
        } catch (SchedulerException e) {
            throw new JobExecutionException("Failed to create or schedule worker job", e);
        }
    }

    private JobDetail createWorkerJobDetail(JobExecutionContext context, String uniqueId) {
        final JobKey workerJobKey = new JobKey(QuartzConfig.BATCH_WORKER_JOB + "_" + uniqueId, QuartzConfig.BATCH_WORKER_JOB_GROUP);
        JobDetail jobDetail = JobBuilder.newJob(BatchWorkerJob.class).storeDurably(TRUE).withIdentity(workerJobKey).withDescription(properties.getProperty(PropsConfig.BATCH_WORKER_JOB_DESCRIPTION)).build();
        jobDetail.getJobDataMap().put(BatchWorkerJob.BATCH_WORKER_JOB_UNIQUE_ID, uniqueId);
        jobDetail.getJobDataMap().put(BatchWorkerJob.BATCH_WORKER_JOB_START_DATE, properties.getProperty(PropsConfig.BATCH_INITIAL_START_DATE));
        return jobDetail;
    }

    private Trigger createWorkerTrigger(String uniqueId) {
        final TriggerKey workerJobTriggerKey = new TriggerKey(QuartzConfig.BATCH_WORKER_JOB_TRIGGER + "_" + uniqueId, QuartzConfig.BATCH_JOB_GROUP);
        return TriggerBuilder.newTrigger().withIdentity(workerJobTriggerKey).startNow().build();
    }

    private Set<Trigger> createWorkerTriggerSet(Trigger... workerJobTrigger) {
        return new HashSet<>(Arrays.asList(workerJobTrigger));
    }

    public static String duration(long startMillis) {
        long durationMillis = System.currentTimeMillis() - startMillis;
        PeriodFormatter formatter = new PeriodFormatterBuilder().appendMinutes().appendSuffix("m").appendSeconds().appendSuffix("s").appendMillis().appendSuffix("ms").toFormatter();
        return formatter.print(new Duration(durationMillis).toPeriod());
    }
}
