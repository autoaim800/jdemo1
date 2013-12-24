package com.billsoft.triptrasvc.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.billsoft.triptrasvc.cli.PoiCli;
import com.billsoft.triptrasvc.parcel.PoiParcel;
import com.billsoft.triptrasvc.request.PoiCreateRequest;
import com.billsoft.triptrasvc.request.PoiUpdateRequest;
import com.billsoft.triptrasvc.response.PoiCreateResponse;
import com.billsoft.triptrasvc.response.SearchResponse;

@Path("poi")
public class PoiResource extends GenericResource {

    /**
     * @param id
     *            an integer of parent poi id
     * @return an object of response
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(PoiCreateRequest cr) {
        
        log.info("poi-resource.create()");

        String token = cr.getToken();
        PoiParcel[] pois = cr.getPois();

        PoiCli pw = new PoiCli();
        int[] poiIds = pw.createPoi(token, pois);

        return Response.ok(new PoiCreateResponse(poiIds)).build();
    }

    /**
     * delete given id of poi
     * 
     * @param token
     *            a string of access token
     * @param id
     *            an integer of poi id (to be deleted)
     * @return an object of response
     */
    @DELETE
    public Response delete(@QueryParam("token") String token, @QueryParam("id") int id) {
        log.info("delete() id=" + id);

        PoiCli pw = new PoiCli();
        int ret = pw.delete(token, id);

        return Response.status(STA_OK).build();
    }

    /**
     * @param id
     *            an integer of poi id
     * @return a string of json
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response search(@QueryParam("id") int id, @QueryParam("lat") float latitude,
            @QueryParam("long") float longitude, @QueryParam("name") String name) {

        log.info(String.format("search() id=%s lat=%s long=%s name=%s\n", id, latitude, longitude,
                name));

        PoiCli pw = new PoiCli();
        PoiParcel[] pois = pw.search(id, latitude, longitude, name);

        SearchResponse sr = new SearchResponse(pois, 1, 10);

        return Response.ok(sr).build();
    }

    /**
     * update given id of poi
     * 
     * @return an object of response
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response update(PoiUpdateRequest ur) {
        log.info("update()");

        String token = ur.getToken();
        PoiParcel[] pois = ur.getPois();

        PoiCli pw = new PoiCli();
        int[] stas = pw.updatePoi(token, pois);

        return Response.status(STA_OK).build();
    }

}
