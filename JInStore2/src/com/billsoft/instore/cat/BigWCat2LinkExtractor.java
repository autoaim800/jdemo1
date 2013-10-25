package com.billsoft.instore.cat;

import java.sql.Connection;

import com.billsoft.instore.BigWLinkBot;
import com.billsoft.instore.LinkExtractorI;


public class BigWCat2LinkExtractor extends BigWLinkBot implements LinkExtractorI {

    public BigWCat2LinkExtractor(Connection connection) {
        super(connection);
    }

    @Override
    public String feed(String url) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int inject() {
        // TODO Auto-generated method stub
        return 0;        
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
