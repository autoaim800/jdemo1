package com.billsoft.instore;

import java.sql.Connection;

import com.billsoft.instore.cat.BigWCatListBuilder;
import com.billsoft.instore.db.SqliteDb;
import com.billsoft.instore.item.BigWItemDataProcesser;
import com.billsoft.instore.item.BigWItemLinkBuilder;

public class JInStore1 {

	public static void main(String[] args) {

		SqliteDb db = new SqliteDb("instore1.db");
		Connection conn = db.open();

		BigWCatListBuilder cb = new BigWCatListBuilder(conn);
		// bcl.inject();
		String[] catUrls = cb.queryCatUrls();

		BigWItemLinkBuilder ib = new BigWItemLinkBuilder(conn);
		for (String catUrl : catUrls) {
			ib.setCatUrl(catUrl);
			ib.inject();
			cb.markCatUrl(catUrl);
		}

		BigWItemDataProcesser idp = new BigWItemDataProcesser(conn);
		for (String itemUrl : ib.queryItemUrls()) {
			idp.setItemUrl(itemUrl);
			idp.inject();
			ib.markItemUrl(itemUrl);
		}

		db.close();
	}

}
