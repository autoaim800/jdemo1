package com.billsoft.instore;

import java.sql.Connection;

import com.billsoft.instore.cat.BigWCat1LinkExtractor;
import com.billsoft.instore.cat.BigWCat2LinkExtractor;
import com.billsoft.instore.db.SqliteDb;
import com.billsoft.instore.item.BigWItemLinkExtractor;

public class JInStore2Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SqliteDb db = new SqliteDb("instore2.db");
        Connection connection = db.open();

        BigWCat1LinkExtractor cb1 = new BigWCat1LinkExtractor(connection);
        cb1.inject();

        BigWCat2LinkExtractor cb2 = new BigWCat2LinkExtractor(connection);
        for (String url : cb1.query()) {
            String hash = cb2.feed(url);
            if (cb2.inject() > 0) {
                // found second level category
                cb2.mark(hash);
            } else {
                // likely this is the second level category
                // do nothing
            }
        }

        // extract item urls
        BigWItemLinkExtractor ie = new BigWItemLinkExtractor(connection);
        for (String url : cb2.query()) {
            String hash = ie.feed(url);
            if (ie.inject() > 0) {
                ie.mark(hash);
            } else {
                // found no useful item link from given url of so called second
                // category
                // do nothing
            }
        }

        // end
        db.close();

    }

}
