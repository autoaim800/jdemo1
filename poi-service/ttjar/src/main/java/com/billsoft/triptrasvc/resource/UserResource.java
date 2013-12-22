package com.billsoft.triptrasvc.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserResource extends GenericResource {

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(String raw) {
        return null;
    }

    /**
     * delete/disable given id of user
     * 
     * @param token
     *            a string of access token
     * @param userId
     *            an integer of user-id, being deleted
     * @return an object of response, success=200
     */
    @DELETE
    public Response delete(@QueryParam("token") String token, @QueryParam("id") int userId) {
        return Response.ok().build();
    }

}
