package com.onlyteo.sandbox.batch;

import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class ConditionalSchedulerFactoryBean extends SchedulerFactoryBean {

    private final boolean enableQuartzTasks;

    public ConditionalSchedulerFactoryBean(boolean enableQuartzTasks) {
        this.enableQuartzTasks = enableQuartzTasks;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (enableQuartzTasks) {
            super.afterPropertiesSet();
        }
    }
}
