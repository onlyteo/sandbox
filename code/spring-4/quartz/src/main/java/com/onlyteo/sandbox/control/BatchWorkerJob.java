package com.onlyteo.sandbox.control;

import org.joda.time.DateTime;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.onlyteo.sandbox.control.BatchJob.duration;

@Component
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class BatchWorkerJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchWorkerJob.class);
    public static final String BATCH_WORKER_JOB_UNIQUE_ID = "BATCH_WORKER_JOB_UNIQUE_ID";
    public static final String BATCH_WORKER_JOB_START_DATE = "BATCH_WORKER_JOB_START_DATE";
    public static final String BATCH_WORKER_JOB_LAST_RUN = "BATCH_WORKER_JOB_LAST_RUN";
    public static final String BATCH_WORKER_JOB_LAST_SUCCESS = "BATCH_WORKER_JOB_LAST_SUCCESS";

    public BatchWorkerJob() {
        LOGGER.info("Batch worker job created");
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        long startMillis = System.currentTimeMillis();
        final JobKey jobKey = context.getJobDetail().getKey();
        final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        final DateTime lastRun = getDateTime(jobDataMap, BATCH_WORKER_JOB_LAST_RUN);
        final DateTime lastSuccess = getDateTime(jobDataMap, BATCH_WORKER_JOB_LAST_SUCCESS);

        String lastRunString = lastRun != null ? lastRun.toString() : "None";
        String lastSuccessString = lastSuccess != null ? lastSuccess.toString() : "None";
        LOGGER.info("Executing batch worker job '{}' for group '{}' (last run: {}) (last success: {})", jobKey.getName(), jobKey.getGroup(), lastRunString, lastSuccessString);
        for (Map.Entry<String, Object> entry : jobDataMap.entrySet()) {
            LOGGER.info("{}: {}", entry.getKey(), entry.getValue().toString());
        }
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            throw new JobExecutionException("Could not sleep", e);
        }

        long endMillis = System.currentTimeMillis();
        jobDataMap.put(BATCH_WORKER_JOB_LAST_RUN, endMillis);
        if (!jobKey.getName().endsWith("_003")) {
            jobDataMap.put(BATCH_WORKER_JOB_LAST_SUCCESS, endMillis);
        }
        LOGGER.info("Completed batch worker job '{}' for group '{}' in {}", jobKey.getName(), jobKey.getGroup(), duration(startMillis));
    }

    private DateTime getDateTime(final JobDataMap jobDataMap, String key) {
        if (jobDataMap.containsKey(key)) {
            return new DateTime(jobDataMap.getLong(key));
        } else {
            return null;
        }
    }
}
