package com.onlyteo.sandbox.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class Foo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Foo.class);

    @Inject
    private Bar bar;

    @PostConstruct
    private void postConstruct() {
        LOGGER.info("Foo init");
    }

    public String foo() {
        LOGGER.info("Inside foo");
        return "FOO! " + (bar == null ? "Bar is null" : bar.bar());
    }
}
