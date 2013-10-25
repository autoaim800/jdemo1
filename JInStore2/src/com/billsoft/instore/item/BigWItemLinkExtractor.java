package com.billsoft.instore.item;

import java.sql.Connection;

import com.billsoft.instore.BigWLinkBot;
import com.billsoft.instore.LinkExtractorI;

public class BigWItemLinkExtractor extends BigWLinkBot implements LinkExtractorI {

    public BigWItemLinkExtractor(Connection connection) {
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
    public void mark(String hash) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String[] query() {
        // TODO Auto-generated method stub
        return null;
    }

}
