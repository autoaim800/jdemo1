package com.billsoft.instore.cat;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class BigWCatListBuilder extends CatListBuilder {

	public final static String BASE_URL = "http://www.bigw.com.au";
	public final static String ENTRY_URL = "http://www.bigw.com.au/bigw/home";
	public final static String HOST_NAME = "www.bigw.com.au";
	public final static String SCHEME = "http";

	private static final String TEXT_CATEGORIES = "Categories";

	public BigWCatListBuilder(Connection conn) {
		super(conn);
	}

	/**
	 * extract category/section entries from given url
	 * 
	 * @param url
	 *            a string of input url
	 * @return a set of string
	 */
	private Set<String> extractCategoryFromEntry(String url) {
		Set<String> pending = new HashSet<String>();

		Document doc = super.httpGetDoc(url);

		if (null == doc) {
			error("no found doc");
			return null;
		}
		Element catSection = doc.getElementById("left-nav-dept-body");
		if (null == catSection) {
			// likely the page does not contain any left-nav-dept-body
			return null;
		}
		Elements h4s = catSection.getElementsByTag("h4");
		if (null == h4s) {
			// a left-nav does not contain any h4
			return null;
		}
		for (Element h4 : h4s) {
			if (TEXT_CATEGORIES.equals(h4.text())) {
				Node firstChild = h4.childNode(0);
				if (NODE_NAME_ANCHOR.equals(firstChild.nodeName())) {
					Element sibling = h4.nextElementSibling();
					if (NODE_NAME_UL.equals(sibling.nodeName())) {
						Elements anchors = sibling
								.getElementsByTag(NODE_NAME_ANCHOR);
						for (Element anchor : anchors) {
							String href = anchor.attr("href");
							if (null != href) {
								href = this.prepareHRef(href);
								if (isValidCatHRef(href)) {
									pending.add(href);
								}
							}
						}
					}
				}
			}
		}

		return pending;
	}

	private boolean isValidCatHRef(String href) {
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
	 * obtain unique entries for the default entry url
	 * 
	 * @return an array of string, not null
	 */
	public void inject() {

		Set<String> hs = new HashSet<String>();

		Document doc = super.httpGetDoc(ENTRY_URL);
		if (null == doc) {
			error("no found doc from root-entry");
			return;
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
		String[] uurls = retrieveEntryLevel2(hs);

		for (String url : uurls) {
			insertCatUrl(url);
		}
	}

	/**
	 * prepare the format of the given href
	 * 
	 * @param href
	 *            a string of href
	 * @return a string of prepared href
	 */
	private String prepareHRef(String href) {
		if (href.startsWith("/")) {
			return String.format("%s://%s%s", SCHEME, HOST_NAME, href);
		}
		return href;
	}

	/**
	 * retrieve level-2 category (if there is any) from level-1 entries, can
	 * never return null
	 * 
	 * @param hs
	 *            a set of href
	 * @return an array of string
	 */
	private String[] retrieveEntryLevel2(Set<String> hs) {
		Set<String> ps = new HashSet<String>();
		for (String url : hs) {
			Set<String> subSet = extractCategoryFromEntry(url);
			if (null == subSet) {
				continue;
			}
			ps.addAll(subSet);
		}
		ps.addAll(hs);
		String[] os = new String[ps.size()];
		ps.toArray(os);
		return os;
	}

	public String[] queryCatUrls() {
		String cmd = "select url from t_categories where status = 0";
		ArrayList<String> pending = new ArrayList<String>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(cmd);
			while (result.next()) {
				String url = result.getString(1);
				pending.add(url);
			}
			stmt.close();

			String[] os = new String[pending.size()];
			pending.toArray(os);
			return os;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * mark given category url as done
	 * 
	 * @param catUrl
	 *            a string of category url
	 */
	public void markCatUrl(String catUrl) {
		String hash = sha1(catUrl);
		String sql = "update t_categories set status = 1 where hash = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, hash);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
