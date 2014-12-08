package com.onlyteo.sandbox.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/redirect")
public class RedirectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedirectController.class);

    @RequestMapping(value = "/from", method = GET)
    public ModelAndView primary(RedirectAttributes redirect) {
        LOGGER.info("Redirect from view");
        redirect.addFlashAttribute("from", "(redirected from here!)");
        return new ModelAndView("redirect:to");
    }

    @RequestMapping(value = "/to", method = GET)
    public ModelAndView secondary(ModelMap model) {
        LOGGER.info("Redirect to view");
        model.addAttribute("message", "this is to!");
        return new ModelAndView("redirect");
    }
}
