package com.onlyteo.sandbox.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class BarImpl implements Bar {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarImpl.class);

    @PostConstruct
    private void postConstruct() {
        LOGGER.info("Bar init");
    }

    @Override
    public String bar() {
        LOGGER.info("Inside bar");
        return "BAR!";
    }
}
