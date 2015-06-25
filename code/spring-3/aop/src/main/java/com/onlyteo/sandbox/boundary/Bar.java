package com.onlyteo.sandbox.boundary;

import com.onlyteo.sandbox.annotation.ApiAnnotation;
import com.onlyteo.sandbox.domain.BarObject;
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

@RequestMapping(value = "/api/bar", produces = APPLICATION_JSON_VALUE)
@Controller
public class Bar {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bar.class);

    @ApiAnnotation
    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    @ResponseBody
    public BarObject get() {
        LOGGER.info("{} GET", this.getClass().getSimpleName());
        return new BarObject("GET");
    }

    @ApiAnnotation
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public BarObject post(@RequestBody BarObject bar) {
        LOGGER.info("{} POST {}", this.getClass().getSimpleName(), bar.getMessage());
        return new BarObject("POST " + bar.getMessage());
    }
}
