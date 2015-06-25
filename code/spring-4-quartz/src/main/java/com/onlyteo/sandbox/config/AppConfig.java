package com.onlyteo.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc
@Import({PropsConfig.class, DatabaseConfig.class, QuartzConfig.class})
@ComponentScan({"com.onlyteo.sandbox.control"})
@Configuration
public class AppConfig {

    public static final String VIEW_PREFIX = "/WEB-INF/views/";
    public static final String VIEW_SUFFIX = ".jsp";

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix(VIEW_PREFIX);
        resolver.setSuffix(VIEW_SUFFIX);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}
