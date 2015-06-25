package com.onlyteo.sandbox.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebApplication implements WebApplicationInitializer {

    public static final String SERVLET_NAME = "BatchScheduler";
    public static final String SERVLET_MAPPING = "/";
    public static final int SERVLET_LOAD_ON_STARTUP = 1;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webApplicationContext = createWebApplicationContext();
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));
        ServletRegistration.Dynamic dynamic = servletContext.addServlet(SERVLET_NAME, new DispatcherServlet(webApplicationContext));
        dynamic.addMapping(SERVLET_MAPPING);
        dynamic.setLoadOnStartup(SERVLET_LOAD_ON_STARTUP);
    }

    private AnnotationConfigWebApplicationContext createWebApplicationContext() {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(AppConfig.class);
        return webApplicationContext;
    }
}
