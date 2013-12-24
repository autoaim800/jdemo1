package com.billsoft.triptrasvc.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.billsoft.triptrasvc.request.LoginRequest;
import com.billsoft.triptrasvc.response.LoginResponse;

@Path("auth")
public class AuthResource extends GenericResource {

    /**
     * @param username
     *            a string of username
     * @param userpass
     *            a string of password hash
     * @return a string of xml, contains access token
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response login(LoginRequest loginRequest) {
        LoginResponse ret = new LoginResponse(DEF_USER_ID, DEF_ACCESS_TOKEN);
        return Response.ok(ret).build();
    }

    /**
     * @param token
     *            a string of access token
     * @return an object of response
     */
    @GET
    public Response logout(@QueryParam("token") String token) {
        if (null == token) {
            log.warning("logout() no found token");
            return Response.noContent().build();
        }
        return Response.ok().build();
    }

}
