package com.onlyteo.sandbox.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FooAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooAspect.class);

    public FooAspect() {
        LOGGER.info("Created!!!");
    }

    @Before("execution(public com.onlyteo.sandbox.boundary.* *(..))")
    public void beforeInvoke(JoinPoint joinPoint) throws Throwable {
        LOGGER.info("Intercepted!!! {}", joinPoint.toString());
        LOGGER.info("Continuing!!!");
    }
}
