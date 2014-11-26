package com.onlyteo.sandbox.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping(value = "/api/foo")
@Controller
public class Foo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Foo.class);
    private static String BAR = "dummy";

    public Foo() {
        LOGGER.info("Created!!!");
    }

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public String get() {
        LOGGER.info("GET!!!");
        return BAR;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(OK)
    @ResponseBody
    public String post(@RequestBody String b) {
        LOGGER.info("POST!!! {}", b);
        BAR = b;
        return BAR;
    }
}
