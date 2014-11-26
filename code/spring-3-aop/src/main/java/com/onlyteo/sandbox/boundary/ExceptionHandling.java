package com.onlyteo.sandbox.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandling {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandling.class);

    public ExceptionHandling() {
        LOGGER.info("Created!!!");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public String methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        return exception.toString();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public String httpMediaTypeNotAcceptableException(HttpServletRequest request, HttpMediaTypeNotAcceptableException exception) {
        return exception.toString();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(HttpServletRequest request, Exception exception) {
        return exception.toString();
    }
}
