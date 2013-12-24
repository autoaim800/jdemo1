package com.billsoft.triptrasvc.resource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.billsoft.triptrasvc.Main;
import com.billsoft.triptrasvc.parcel.PoiParcel;
import com.billsoft.triptrasvc.request.PoiCreateRequest;
import com.billsoft.triptrasvc.request.PoiUpdateRequest;
import com.billsoft.triptrasvc.response.SearchResponse;

public class PoiResourceTest extends GenericResource {

    private HttpServer server;
    private WebTarget target;
    private WebTarget tar;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and
        // Main.startServer())
        // --
        // c.configuration().enable(new
        // org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
        tar = target.path("poi");
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testSearch() {
        tar = target.path("poi");
        tar = tar.queryParam("id", 10);
        Builder b = tar.request();
        Invocation inv = b.buildGet();
        Response resp = inv.invoke();

        SearchResponse sr = resp.readEntity(SearchResponse.class);
        log.info(sr.toString());

    }

    @Test
    public void testDelete() {
        tar = target.path("poi");
        tar = tar.queryParam("id", 10).queryParam("token", "abc");
        Builder b = tar.request();
        Invocation inv = b.buildDelete();
        Response resp = inv.invoke();
    }

    @Test
    public void testCreate() {
        Builder b = tar.request();
        PoiParcel poi = new PoiParcel(0, 2);
        Invocation inv = b.buildPost(Entity.xml(new PoiCreateRequest(new PoiParcel[] { poi },
                "a98fEz")));
        Response resp = inv.invoke();

    }

    @Test
    public void testUpdate() {
        Builder b = tar.request();
        PoiParcel poi = new PoiParcel(0, 2);
        Invocation inv = b.buildPut(Entity.xml(new PoiUpdateRequest(new PoiParcel[] { poi },
                "a98fEz")));
        Response resp = inv.invoke();
    }
}
