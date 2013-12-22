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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.billsoft.triptrasvc.parcel.PoiCreateRequest;
import com.billsoft.triptrasvc.parcel.PoiParcel;
import com.billsoft.triptrasvc.parcel.PoiUpdateRequest;
import com.billsoft.triptrasvc.parcel.SearchResponse;

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
        log.info("create()");
        return Response.status(STA_OK).build();
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

        PoiParcel[] pois = new PoiParcel[] { new PoiParcel(0, 2), new PoiParcel(0, 3) };
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
        return Response.status(STA_OK).build();
    }

}
