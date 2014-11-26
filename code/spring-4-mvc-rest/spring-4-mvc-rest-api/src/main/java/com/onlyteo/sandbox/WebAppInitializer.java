package com.onlyteo.sandbox;

import com.onlyteo.sandbox.config.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webApplicationContext = createWebApplicationContext();
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(webApplicationContext));
        dynamic.addMapping("/api/*");
        dynamic.setLoadOnStartup(1);
    }

    private AnnotationConfigWebApplicationContext createWebApplicationContext() {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(AppConfig.class);
        return webApplicationContext;
    }
}
