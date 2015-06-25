package com.onlyteo.sandbox.control;

import com.onlyteo.sandbox.config.QuartzConfig;
import com.onlyteo.sandbox.domain.BatchGroupVO;
import com.onlyteo.sandbox.domain.BatchJobVO;
import com.onlyteo.sandbox.domain.BatchTriggerVO;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class Views {

    private final SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    public Views(@Qualifier(QuartzConfig.BATCH_JOB_SCHEDULER) final SchedulerFactoryBean schedulerFactoryBean) {
        this.schedulerFactoryBean = schedulerFactoryBean;
    }

    @RequestMapping(value = "/", method = GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/list", method = GET)
    public String list(ModelMap model) {
        model.addAttribute("batchGroups", retrieveBatchGroups());
        return "list";
    }

    @RequestMapping(value = "/start", method = GET)
    public String start(ModelMap model) {
        model.addAttribute("started", startBatchJob() ? "Started" : "Could not start");
        return "start";
    }

    private List<BatchGroupVO> retrieveBatchGroups() {
        try {
            final List<BatchGroupVO> batchGroups = new ArrayList<>();
            final Scheduler scheduler = schedulerFactoryBean.getScheduler();
            final List<String> groupNames = scheduler.getJobGroupNames();
            for (String groupName : groupNames) {
                final List<BatchJobVO> batchJobs = new ArrayList<>();
                final Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName));
                for (JobKey jobKey : jobKeys) {
                    final JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    final List<BatchTriggerVO> triggers = new ArrayList<>();
                    final List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
                    for (Trigger trigger : triggersOfJob) {
                        final Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                        triggers.add(new BatchTriggerVO(trigger, triggerState));
                    }
                    batchJobs.add(new BatchJobVO(jobDetail, triggers));
                }
                batchGroups.add(new BatchGroupVO(groupName, batchJobs));
            }
            return batchGroups;
        } catch (SchedulerException e) {
            throw new RuntimeException("Failed to retrieve batch jobs", e);
        }
    }

    private boolean startBatchJob() {
        try {
            final JobKey jobKey = new JobKey(QuartzConfig.BATCH_JOB, QuartzConfig.BATCH_JOB_GROUP);
            final Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if (scheduler.checkExists(jobKey)) {
                scheduler.triggerJob(jobKey);
                return Boolean.TRUE;
            }
        } catch (SchedulerException e) {
            // Ignore
        }
        return Boolean.FALSE;
    }
}
