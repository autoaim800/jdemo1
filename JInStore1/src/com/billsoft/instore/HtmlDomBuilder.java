package com.billsoft.instore;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlDomBuilder extends GenericBuilder {

	private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.112 Safari/534.30";
	public static final String NODE_NAME_ANCHOR = "a";
	public static final String NODE_ATTR_HREF = "href";
	public static final String NODE_NAME_UL = "ul";

	protected boolean useZip = false;

	/**
	 * retrieve html content for given entryUrl then parse the content into
	 * document
	 * 
	 * @param url
	 *            a string of entry-url
	 * @return an object of <code>Document</code> or null for exception
	 */
	public Document httpGetDoc(String url) {
		info(url);
		Connection connection = Jsoup.connect(url);
		simulateChrome(connection);
		for (int i = 0; i < 3; i++) {
			try {
				return connection.get();
			} catch (SocketTimeoutException e) {
				// ignored
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		return null;
	}

	/**
	 * configure the connection
	 * 
	 * @param conn
	 *            an object of <code>connection</code>
	 */
	private void simulateChrome(Connection conn) {
		conn.userAgent(USER_AGENT);
		if (this.useZip) {
			conn.header("accept-encoding", "gzip");
		}
		conn.timeout(9000);
	}

}
