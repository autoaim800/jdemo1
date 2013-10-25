package com.billsoft.instore.cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.billsoft.instore.BigWLinkBot;
import com.billsoft.instore.LinkExtractorI;

public class BigWCat1LinkExtractor extends BigWLinkBot implements LinkExtractorI {

    public final static String ENTRY_URL = "http://www.bigw.com.au/bigw/home";
    private String feedUrl;

    public BigWCat1LinkExtractor(Connection connection) {
        super(connection);
        feedUrl = ENTRY_URL;
    }

    @Override
    public String feed(String url) {
        return null;
    }

    @Override
    public int inject() {
        Set<String> hs = new HashSet<String>();

        Document doc = super.httpGetDoc(ENTRY_URL);
        if (null == doc) {
            error("no found doc from root-entry");
            return 0;
        }
        Element menuNode = doc.getElementById("menu");
        Elements anchors = menuNode.getElementsByTag("a");
        for (Element anchor : anchors) {
            String href = anchor.attr("href");
            if (null == href || 1 > href.length()) {
                continue;
            }
            href = prepareHRef(href);
            if (isValidCatHRef(href)) {
                hs.add(href);
            }
        }

        return insertCatHashSet(hs);
    }

    private int insertCatHashSet(Set<String> hs) {
        int sum = 0;
        for (String url : hs) {
            if(            insertCat1Url(url)){
                sum ++;
            }
        }
        return sum;
    }

    private boolean insertCat1Url(String url) {
        String hash = sha1(url);
        String sql = "insert into t_urls (host, hash, url, status) values (?, ?, ?, 0)";

        PreparedStatement ps = null;
        boolean ret = false;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, HOST_NAME);
            ps.setString(2, hash);
            ps.setString(3, url);
            ps.execute();
            ret = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // ignored
                }
                ps = null;
            }
        }
        return ret;
    }

    @Override
    public void mark(String urlHash) {
        // TODO Auto-generated method stub

    }

    @Override
    public String[] query() {
        // TODO Auto-generated method stub
        return null;
    }

}
