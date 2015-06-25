package com.onlyteo.sandbox.boundary;

import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/foo")
@Produces(MediaType.TEXT_PLAIN)
public class Foo {

    private static String BAR = "";

    @GET
    public String get() {
        return BAR;
    }

    @POST
    public void post(@NotEmpty String b) {
        BAR = b;
    }
}
