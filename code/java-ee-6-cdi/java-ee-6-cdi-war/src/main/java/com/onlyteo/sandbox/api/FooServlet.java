package com.onlyteo.sandbox.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/foo")
public class FooServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooServlet.class);

    @Inject
    private Foo foo;

    @PostConstruct
    private void postConstruct() {
        LOGGER.info("Servlet init");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Inside servlet");
        response.getWriter().println(foo == null ? "Foo is null" : foo.foo());
    }
}
