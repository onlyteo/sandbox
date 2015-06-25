package com.onlyteo.sandbox.domain;

import org.quartz.JobDetail;

import java.util.List;

public class BatchJobVO {

    private JobDetail jobDetail;
    private List<BatchTriggerVO> batchTriggers;

    public BatchJobVO() {
    }

    public BatchJobVO(JobDetail jobDetail, List<BatchTriggerVO> batchTriggers) {
        this.jobDetail = jobDetail;
        this.batchTriggers = batchTriggers;
    }

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }

    public List<BatchTriggerVO> getBatchTriggers() {
        return batchTriggers;
    }

    public void setBatchTriggers(List<BatchTriggerVO> batchTriggers) {
        this.batchTriggers = batchTriggers;
    }
}
