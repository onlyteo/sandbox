package com.onlyteo.sandbox.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.onlyteo.sandbox.util.ResponseUtil.badRequest;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return badRequest(exception.getMessage());
    }
}
