package com.billsoft.triptrasvc.resource;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.billsoft.triptrasvc.Main;

public class UserResourceTest extends GenericResource {

    private HttpServer server;
    private WebTarget target;

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
        target = target.path("poi");
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testDelete() {
        target = target.queryParam("id", 10).queryParam("token", "a-long-token");
        Builder b = target.request();
        Invocation inv = b.buildDelete();
        Response resp = inv.invoke();
        assertEquals(STA_OK, resp.getStatus());
        // assertEquals(STA_NO_CONTENT, resp.getStatus());
    }

}
