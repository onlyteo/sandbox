package acntech.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan({"acntech.boundary"})
@Configuration
@EnableWebMvc
public class ApplicationConfig {
}
