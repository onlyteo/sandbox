package com.onlyteo.sandbox.boundary;

import com.onlyteo.sandbox.domain.FooObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping(value = "/api/foo", produces = APPLICATION_JSON_VALUE)
@Controller
public class Foo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Foo.class);

    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    @ResponseBody
    public FooObject get() {
        LOGGER.info("{} GET", this.getClass().getSimpleName());
        return new FooObject("GET");
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public FooObject post(@RequestBody FooObject bar) {
        LOGGER.info("{} POST {}", this.getClass().getSimpleName(), bar.getMessage());
        return new FooObject("POST " + bar.getMessage());
    }
}
