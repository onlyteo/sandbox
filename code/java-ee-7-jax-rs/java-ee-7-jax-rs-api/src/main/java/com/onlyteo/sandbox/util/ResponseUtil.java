package com.onlyteo.sandbox.util;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public final class ResponseUtil {

    private ResponseUtil() {
    }

    public static Response ok(Object entity) {
        return Response.ok(entity).build();
    }

    public static Response ok(Object entity, UUID requestId) {
        return Response.ok(entity).header("requestId", requestId.toString()).build();
    }

    public static Response created(String resource, String id) {
        URI location = location(resource, id);
        return Response.created(location).build();
    }

    public static Response badRequest(Object entity) {
        return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
    }

    public static URI location(String resource, String id) {
        try {
            return new URI("http://localhost:8080/java-ee-7-jax-rs-app/app/" + resource + "/" + id);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Bad URI syntax", e);
        }
    }
}
