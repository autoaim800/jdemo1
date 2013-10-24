package com.billsoft.instore.item;

import java.sql.Connection;

public class BigWItemDataProcesser extends ItemDataProcesser {

	private String itemUrl;

	public BigWItemDataProcesser(Connection conn) {
		// TODO Auto-generated constructor stub
	}

	public void processAll() {

	}

	public void setItemUrl(String url) {
		itemUrl = url;
	}

	/**
	 * inject item data to dB
	 */
	public void inject() {
		// parse data from item-view-page
		// inject to dB
	}

}
