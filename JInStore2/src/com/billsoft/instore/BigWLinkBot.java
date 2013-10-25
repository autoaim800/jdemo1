package com.billsoft.instore;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

public class BigWLinkBot extends HtmlDomBuilder{

    protected Connection connection;
    public final static String BASE_URL = "http://www.bigw.com.au";    
    public final static String HOST_NAME = "www.bigw.com.au";
    public final static String SCHEME = "http";

    public BigWLinkBot(Connection conn) {
        connection = conn;
    }
    
    /**
     * prepare the format of the given href
     * 
     * @param href
     *            a string of href
     * @return a string of prepared href
     */
    protected String prepareHRef(String href) {
        if (href.startsWith("/")) {
            return String.format("%s://%s%s", SCHEME, HOST_NAME, href);
        }
        return href;
    }
    
    protected boolean isValidCatHRef(String href) {
        if (href.startsWith(BASE_URL)) {
            try {
                URL url = new URL(href);
                String path = url.getPath();
                if (path.startsWith("/bigw/")
                        || path.startsWith("/buying-guide/")
                        || path.startsWith("/offer/")
                        || path.startsWith("/list/")) {
                    return false;
                }
                return true;
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }


}
