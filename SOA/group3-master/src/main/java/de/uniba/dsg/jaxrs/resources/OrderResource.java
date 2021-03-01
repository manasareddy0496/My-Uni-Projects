package de.uniba.dsg.jaxrs.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;


public class OrderResource {

    private static final Logger logger = Logger.getLogger("BeverageResource");

    /*
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response placeNewOrder(@PathParam("BeverageName") String beverageName, @PathParam("Quantity") int quantity)
    {
        int id = (int) Math.random();

        return Response.ok("Order placed" + id).build();
    }
    */

}
