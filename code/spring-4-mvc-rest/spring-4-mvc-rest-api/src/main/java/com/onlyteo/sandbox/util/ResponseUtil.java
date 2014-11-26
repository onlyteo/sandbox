package com.onlyteo.sandbox.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public final class ResponseUtil {

    private ResponseUtil() {
    }

    public static <T> ResponseEntity ok(T entity) {
        return new ResponseEntity<T>(entity, HttpStatus.OK);
    }

    public static <T> ResponseEntity ok(T entity, UUID requestId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Request-ID", requestId.toString());
        return new ResponseEntity<T>(entity, responseHeaders, HttpStatus.OK);
    }

    public static ResponseEntity created(String resource, String id) {
        URI location = location(resource, id);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public static <T> ResponseEntity badRequest(T entity) {
        return new ResponseEntity<T>(entity, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity badRequest() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public static URI location(String resource, String id) {
        try {
            return new URI("http://localhost:8080/java-ee-7-jax-rs-app/app/" + resource + "/" + id);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Bad URI syntax", e);
        }
    }
}
