package com.onlyteo.sandbox.resources;

import com.onlyteo.sandbox.backend.FooService;
import com.onlyteo.sandbox.domain.Foo;
import com.onlyteo.sandbox.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.onlyteo.sandbox.util.ResponseUtil.badRequest;
import static com.onlyteo.sandbox.util.ResponseUtil.ok;

@RestController
@RequestMapping("/foo")
public class FooResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooResource.class);

    @Autowired
    private FooService fooService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@RequestParam(value = "id", required = false) String id) {
        final UUID requestId = RequestUtil.createRequestId();
        //request.setAttribute("requestId", requestId);
        LOGGER.info("Request ID: {}", requestId);
        if (id == null) {
            List<Foo> fooList = fooService.list();
            return ok(fooList);
        } else {
            Foo foo = fooService.read(id);
            return ok(foo);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(Foo foo) {
        final UUID requestId = RequestUtil.createRequestId();
        //request.setAttribute("requestId", requestId);
        LOGGER.info("Request ID: {}", requestId);
        Foo fooCreated = fooService.create(foo);
        return ok(fooCreated);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(Foo foo) {
        final UUID requestId = RequestUtil.createRequestId();
        //request.setAttribute("requestId", requestId);
        LOGGER.info("Request ID: {}", requestId);
        Foo fooUpdated = fooService.update(foo);
        return ok(fooUpdated);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(String id) {
        final UUID requestId = RequestUtil.createRequestId();
        //request.setAttribute("requestId", requestId);
        LOGGER.info("Request ID: {}", requestId);
        Foo foo = fooService.delete(id);
        return ok(foo);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException() {
        return badRequest();
    }
}
