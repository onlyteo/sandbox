package com.onlyteo.sandbox.domain;

import org.quartz.Trigger;

public class BatchTriggerVO {

    private Trigger trigger;
    private Trigger.TriggerState state;

    public BatchTriggerVO() {
    }

    public BatchTriggerVO(Trigger trigger, Trigger.TriggerState state) {
        this.trigger = trigger;
        this.state = state;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public Trigger.TriggerState getState() {
        return state;
    }

    public void setState(Trigger.TriggerState state) {
        this.state = state;
    }
}
