package com.onlyteo.sandbox.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan({"com.onlyteo.sandbox.boundary"})
@Configuration
@EnableWebMvc
public class ApplicationConfig {
}
