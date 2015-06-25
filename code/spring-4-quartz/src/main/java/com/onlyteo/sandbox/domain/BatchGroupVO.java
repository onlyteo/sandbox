package com.onlyteo.sandbox.domain;

import java.util.List;

public class BatchGroupVO {

    private String name;
    private List<BatchJobVO> batchJobs;

    public BatchGroupVO() {
    }

    public BatchGroupVO(String name, List<BatchJobVO> batchJobs) {
        this.name = name;
        this.batchJobs = batchJobs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BatchJobVO> getBatchJobs() {
        return batchJobs;
    }

    public void setBatchJobs(List<BatchJobVO> batchJobs) {
        this.batchJobs = batchJobs;
    }
}
