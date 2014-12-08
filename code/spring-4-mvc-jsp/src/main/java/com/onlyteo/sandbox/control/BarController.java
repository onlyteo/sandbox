package com.onlyteo.sandbox.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class BarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarController.class);

    @RequestMapping(value = "/bar", method = GET)
    public ModelAndView bar(ModelMap model) {
        LOGGER.info("Bar view");
        model.addAttribute("message", "this is bar!");
        return new ModelAndView("bar");
    }
}
