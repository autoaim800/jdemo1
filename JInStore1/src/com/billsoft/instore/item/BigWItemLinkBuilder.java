package com.billsoft.instore.item;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * builder class extracts item links from category page
 */
public class BigWItemLinkBuilder extends ItemLinkBuilder {
	public final static String BASE_URL = "http://www.bigw.com.au";
	public final static String ENTRY_URL = "http://www.bigw.com.au/bigw/home";
	public final static String HOST_NAME = "www.bigw.com.au";
	public final static String SCHEME = "http";
	private String entryUrl;

	public BigWItemLinkBuilder(Connection conn) {
		super(conn);
	}

	private boolean isValidItemUrl(String href) {
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

	/**
	 * inject unique item links from given category url
	 * 
	 * @param catUrl
	 *            a string of url
	 * @return a set of string, empty set for no found
	 */
	public void inject() {
		Document doc = httpGetDoc(entryUrl);
		if (null == doc) {
			error("null doc from url: " + entryUrl);
			return;
		}
		Element divMain = doc.getElementById("main");
		if (null == divMain) {
			error("no found div#main from given cat:" + entryUrl);
			return;
		}
		Elements elements = divMain.getElementsByTag(NODE_NAME_ANCHOR);
		if (null == elements) {
			error("div#main contains no anchor");
			return;
		}
		for (Element anchor : elements) {
			String href = anchor.attr(NODE_ATTR_HREF);
			if (null == href) {
				continue;
			}
			href = prepareHRef(href);
			if (isValidItemUrl(href)) {
				insertItemUrl(href);
			}
		}
		return;
	}

	/**
	 * give proper format for given url
	 * 
	 * @param href
	 *            a string href
	 * @return a string of properred href
	 */
	private String prepareHRef(String href) {
		if (href.startsWith("/")) {
			href = String.format("%s%s", BASE_URL, href);
		}
		return href;
	}

	public void setCatUrl(String catUrl) {
		entryUrl = catUrl;

	}

	public String[] queryItemUrls() {
		// TODO Auto-generated method stub
		return null;
	}

	/**mark item as done
	 * @param itemUrl
	 */
	public void markItemUrl(String itemUrl) {
		// TODO Auto-generated method stub
		
	}

}
