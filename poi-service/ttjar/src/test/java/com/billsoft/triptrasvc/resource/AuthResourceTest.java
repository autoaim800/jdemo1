package com.billsoft.triptrasvc.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import com.billsoft.triptrasvc.parcel.LoginRequest;
import com.billsoft.triptrasvc.parcel.LoginResponse;

public class AuthResourceTest extends GenericResource {

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
        target = target.path("auth");
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testLogin() {
        log.info("testLogin()");
        Builder b = target.request();
        Invocation inv = b.buildPost(Entity.xml(new LoginRequest("user1", "hash-of-password")));
        Response resp = inv.invoke();
        assertEquals(STA_OK, resp.getStatus());

        assertTrue(resp.hasEntity());
        LoginResponse lr = resp.readEntity(LoginResponse.class);
        assertEquals(DEF_ACCESS_TOKEN, lr.getToken());
        assertEquals(DEF_USER_ID, lr.getId());
    }

    @Test
    public void testLogout() {
        target = target.queryParam("token", "ad87F");
        Builder b = target.request();
        Invocation inv = b.buildGet();
        Response resp = inv.invoke();
        assertEquals(STA_OK, resp.getStatus());
    }
}
