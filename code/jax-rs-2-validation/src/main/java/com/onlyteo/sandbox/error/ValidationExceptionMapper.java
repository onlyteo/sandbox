package com.onlyteo.sandbox.error;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        if (exception instanceof ConstraintViolationException) {
            StringBuilder stringBuilder = new StringBuilder();
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) exception).getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                stringBuilder.append(violation.getMessage()).append("\n");
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(stringBuilder.toString()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.toString()).build();
        }
    }
}
