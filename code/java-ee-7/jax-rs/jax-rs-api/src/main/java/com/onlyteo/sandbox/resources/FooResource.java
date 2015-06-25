package com.onlyteo.sandbox.resources;

import com.onlyteo.sandbox.backend.FooService;
import com.onlyteo.sandbox.domain.Foo;
import com.onlyteo.sandbox.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

import static com.onlyteo.sandbox.util.ResponseUtil.ok;

@Path("foo")
@Produces(MediaType.APPLICATION_JSON)
public class FooResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooResource.class);

    @Inject
    private FooService service;

    @Inject
    private HttpServletRequest request;

    @GET
    public Response get() {
        final UUID requestId = RequestUtil.createRequestId();
        request.setAttribute("requestId", requestId);
        LOGGER.info("Request ID: {}", requestId);
        List<Foo> fooList = service.list();
        return ok(fooList);
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id) {
        final UUID requestId = RequestUtil.createRequestId();
        request.setAttribute("requestId", requestId);
        LOGGER.info("Request ID: {}", requestId);
        Foo foo = service.read(id);
        return ok(foo);
    }

    @POST
    public Response post(Foo foo) {
        final UUID requestId = RequestUtil.createRequestId();
        request.setAttribute("requestId", requestId);
        LOGGER.info("Request ID: {}", requestId);
        Foo fooCreated = service.create(foo);
        return ok(fooCreated);
    }

    @PUT
    public Response put(Foo foo) {
        final UUID requestId = RequestUtil.createRequestId();
        request.setAttribute("requestId", requestId);
        LOGGER.info("Request ID: {}", requestId);
        Foo fooUpdated = service.update(foo);
        return ok(fooUpdated);
    }

    @DELETE
    public Response delete(String id) {
        final UUID requestId = RequestUtil.createRequestId();
        request.setAttribute("requestId", requestId);
        LOGGER.info("Request ID: {}", requestId);
        Foo foo = service.delete(id);
        return ok(foo);
    }
}
