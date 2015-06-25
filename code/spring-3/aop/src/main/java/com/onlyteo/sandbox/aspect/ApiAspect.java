package com.onlyteo.sandbox.aspect;

import com.onlyteo.sandbox.domain.BarObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAspect.class);

    @Pointcut("execution(public * com.onlyteo.sandbox.boundary.*.*(..))")
    public void anyApiMethod() {
    }

    @Pointcut("@annotation(com.onlyteo.sandbox.annotation.ApiAnnotation)")
    public void annotatedApiMethod() {

    }

    @Before("anyApiMethod() && !annotatedApiMethod()")
    public void beforeInvokeFoo(JoinPoint joinPoint) throws Throwable {
        LOGGER.info("Foo intercepted");
        LOGGER.info("Kind: {}", joinPoint.getKind());
        LOGGER.info("Signature {}", joinPoint.getSignature().toString());
        LOGGER.info("Target {}", joinPoint.getTarget().getClass());
        for (Object arg : joinPoint.getArgs()) {
            LOGGER.info("Arg {}", arg.toString());
        }
    }

    @Around("anyApiMethod() && annotatedApiMethod()")
    public Object aroundInvokeBar(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("Bar intercepted");
        LOGGER.info("Kind: {}", joinPoint.getKind());
        LOGGER.info("Signature {}", joinPoint.getSignature().toString());
        LOGGER.info("Target {}", joinPoint.getTarget().getClass());
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BarObject) {
                BarObject barObject = (BarObject) arg;
                barObject.setMessage(barObject.getMessage() + " (intercepted)");
            }
            LOGGER.info("Arg {}", arg.getClass().getName());
        }
        return joinPoint.proceed();
    }
}
