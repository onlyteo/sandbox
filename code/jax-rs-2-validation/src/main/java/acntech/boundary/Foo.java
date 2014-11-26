package acntech.boundary;

import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/foo")
@Produces(MediaType.TEXT_PLAIN)
public class Foo {

    private static String foo = "";

    @GET
    public String get() {
        return foo;
    }

    @POST
    public void post(@NotEmpty String foo) {
        this.foo = foo;
    }
}
