package com.billsoft.instore.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.billsoft.instore.HtmlDomBuilder;

public class ItemLinkBuilder extends HtmlDomBuilder {

	private static final String TABLE_NAME = "t_items";
	private static final String CREATE_TABLE = "create table t_items "
			+ "(hash varchar(45) primary key, " + "url text, "
			+ "status default 0)";
	private Connection connection;

	public ItemLinkBuilder(Connection conn) {
		connection = conn;
		forceExistsTable(connection, TABLE_NAME, CREATE_TABLE);
	}

	protected void insertItemUrl(String url) {
		String hash = sha1(url);
		String sql = "insert into t_items (hash, url, status) values (?, ?, 0)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, hash);
			pstmt.setString(2, url);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (19 == e.getErrorCode()) {
				// do nothing
			} else {
				e.printStackTrace();
				error(url);
			}
		}
	}

}
