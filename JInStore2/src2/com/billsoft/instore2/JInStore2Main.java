package com.billsoft.instore2;

import com.billsoft.instore2.impl.BigWLinkExtractor;
import com.billsoft.instore2.impl.Sqlite3Db;

public class JInStore2Main {

    public static void main(String[] args) {
        Dbable db = new Sqlite3Db("instore2.db");
        db.open();

        LinkExtractable le = new BigWLinkExtractor(db);
        le.extractCategoryLinks();
        le.extractItemLinks();
        le.extractItemData();

        db.close();
    }
}
