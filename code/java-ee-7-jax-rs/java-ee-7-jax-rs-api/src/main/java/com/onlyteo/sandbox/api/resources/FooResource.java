package com.onlyteo.sandbox.api.resources;

import com.onlyteo.sandbox.backend.domain.Foo;
import com.onlyteo.sandbox.backend.services.FooService;

import javax.inject.Inject;
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

@Path("foo")
@Produces(MediaType.APPLICATION_JSON)
public class FooResource {

    @Inject
    private FooService service;

    @GET
    public Response get() {
        List<Foo> fooList = service.list();
        return Response.ok(fooList).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id) {
        Foo foo = service.read(id);
        return Response.ok(foo).build();
    }

    @POST
    public Response post(Foo foo) {
        Foo fooCreated = service.create(foo);
        return Response.ok(fooCreated).build();
    }

    @PUT
    public Response put(Foo foo) {
        Foo fooUpdated = service.update(foo);
        return Response.ok(fooUpdated).build();
    }

    @DELETE
    public Response delete(String id) {
        Foo foo = service.delete(id);
        return Response.ok(foo).build();
    }
}
