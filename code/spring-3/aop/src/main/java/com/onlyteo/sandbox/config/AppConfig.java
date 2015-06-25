package com.onlyteo.sandbox.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan({"com.onlyteo.sandbox.boundary", "com.onlyteo.sandbox.error", "com.onlyteo.sandbox.aspect"})
@Configuration
public class AppConfig {
}
