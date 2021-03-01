package de.uniba.dsg.jaxrs.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import de.uniba.dsg.jaxrs.db.DB;
import de.uniba.dsg.jaxrs.model.Bottle;
import de.uniba.dsg.jaxrs.model.Crate;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("Beverages")
public class BeverageResource {

    private int bottleId;
    private int crateId;

    @Path("bottles/getAllBottles")
    @GET
    public Response getAllBottles() {
        DB db = new DB();
        List<Bottle> listOfBottles = new ArrayList<Bottle>();
        listOfBottles = db.getBottles();

        if (listOfBottles != null) {
            return Response.status(Response.Status.OK).entity(listOfBottles).build();
        }
        else
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Path("crates/getAllCrates")
    @GET
    public Response getAllCrates()  {
        DB db = new DB();
        List <Crate> listOfCrates = new ArrayList<Crate>();
        listOfCrates = db.getCrates();

        if (listOfCrates != null) {
            return Response.status(Response.Status.OK).entity(listOfCrates).build();
        }
        else
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Path("bottles/getById/{bottleId}")
    @GET
    public Response getBottleById(@PathParam("bottleId") String bottleId) {

        try {
            this.bottleId = Integer.parseInt(bottleId);
        } catch (Exception ex) {
            return Response.status(400, "No valid number was passed").build();
        }
        DB db = new DB();
        List<Bottle> listOfBottles = new ArrayList<Bottle>();
        Boolean foundFlag = false;
        Bottle bottle = null;

        if (listOfBottles.isEmpty()) {
            return Response.status(200, "No Bottles available").build();
        }

            int index = 0;
            for (Bottle b : listOfBottles) {
                if (b.getId() == this.bottleId) {
                    foundFlag = true;
                    bottle = new Bottle();
                    bottle = listOfBottles.get(index);
                    break;
                } else {
                    index = index + 1;
                }
            }


        if (foundFlag) {
            return Response.status(200, "").entity(bottle).build();
        } else {
            return Response.status(200, "Bottle with id " + this.bottleId + " not found").build();
        }
    }

    @Path("bottles/maxPrice")
    @GET
    public Response getBottleMaxPrice()
    {
        DB db = new DB();
        List<Bottle> listOfBottles = new ArrayList<Bottle>();
        listOfBottles =  db.getBottles();

        if(listOfBottles.isEmpty())
        {
            return Response.status(200, "No Bottles available").build();
        }

         listOfBottles.sort((Bottle b1, Bottle b2)-> Double.compare(b2.getPrice(), b1.getPrice()));
         Bottle bottle = new Bottle();
         bottle = listOfBottles.get(0);

         return Response.status(200, "").entity(bottle).build();

    }

    @Path("bottles/minPrice")
    @GET
    public Response getBottleMinPrice()
    {
        DB db = new DB();
        List<Bottle> listOfBottles = new ArrayList<Bottle>();
        listOfBottles =  db.getBottles();

        if(listOfBottles.isEmpty())
        {
            return Response.status(200, "No Bottles available").build();
        }

        listOfBottles.sort((Bottle b1, Bottle b2)-> Double.compare(b2.getPrice(), b1.getPrice()));
        Bottle bottle = new Bottle();
        bottle = listOfBottles.get(listOfBottles.size()-1);

        return Response.status(200, "").entity(bottle).build();

    }

    @Path("crates/getById/{crateId}")
    @GET
    public Response getCrateById(@PathParam("crateId") String crateId) {

        try {
            this.crateId = Integer.parseInt(crateId);
        } catch (Exception ex) {
            return Response.status(400, "No valid number was passed").build();
        }
        DB db = new DB();
        List<Crate> listOfCrates = new ArrayList<Crate>();
        Boolean foundFlag = false;
        Crate crate = null;

        if (listOfCrates.isEmpty()) {
            return Response.status(200, "No Crates available").build();
        }

        int index = 0;
        for (Crate c : listOfCrates) {
            if (c.getId() == this.bottleId) {
                foundFlag = true;
                crate = new Crate();
                crate = listOfCrates.get(index);
                break;
            } else {
                index = index + 1;
            }
        }


        if (foundFlag) {
            return Response.status(200, "").entity(crate).build();
        } else {
            return Response.status(200, "Crate with id " + this.crateId + " not found").build();
        }
    }

    @Path("crates/maxPrice")
    @GET
    public Response getCrateMaxPrice()
    {
        DB db = new DB();
        List<Crate> listOfCrates = new ArrayList<Crate>();
        listOfCrates =  db.getCrates();

        if(listOfCrates.isEmpty())
        {
            return Response.status(200, "No Crates available").build();
        }

        listOfCrates.sort((Crate c1, Crate c2)-> Double.compare(c2.getPrice(), c1.getPrice()));
        Crate crate = new Crate();
        crate = listOfCrates.get(0);

        return Response.status(200, "").entity(crate).build();

    }

    @Path("crates/minPrice")
    @GET
    public Response getCrateMinPrice()
    {
        DB db = new DB();
        List<Crate> listOfCrates = new ArrayList<Crate>();
        listOfCrates =  db.getCrates();

        if(listOfCrates.isEmpty())
        {
            return Response.status(200, "No Crates available").build();
        }

        listOfCrates.sort((Crate c1, Crate c2)-> Double.compare(c2.getPrice(), c1.getPrice()));
        Crate crate = new Crate();
        crate = listOfCrates.get(listOfCrates.size()-1);

        return Response.status(200, "").entity(crate).build();

    }

}
