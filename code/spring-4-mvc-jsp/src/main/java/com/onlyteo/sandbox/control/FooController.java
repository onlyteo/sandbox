package com.onlyteo.sandbox.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class FooController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooController.class);

    @RequestMapping(value = "/foo", method = GET)
    public String foo(ModelMap model) {
        LOGGER.info("Foo view");
        model.addAttribute("message", "this is foo!");
        return "foo";
    }
}
