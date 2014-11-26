package com.onlyteo.sandbox.config;

import com.onlyteo.sandbox.backend.FooService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.onlyteo.sandbox.resources"})
@EnableWebMvc
public class AppConfig {

    @Bean
    public FooService fooService() {
        return new FooService();
    }
}
