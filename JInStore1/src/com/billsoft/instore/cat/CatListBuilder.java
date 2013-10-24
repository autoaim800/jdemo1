package com.billsoft.instore.cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.billsoft.instore.HtmlDomBuilder;

public class CatListBuilder extends HtmlDomBuilder {

	private static final String TABLE_NAME = "t_categories";
	private static final String CREATE_TABLE = "create table t_categories "
			+ "(hash varchar(45) primary key, " + "url text, "
			+ "status int default 0);";
	protected Connection connection;

	public CatListBuilder(Connection conn) {
		info("CatListBuilder()");
		connection = conn;
		forceExistsTable(connection, TABLE_NAME, CREATE_TABLE);
	}

	protected void insertCatUrl(String url) {
		String hash = sha1(url);
		String sql = "insert into t_categories (hash, url, status) values (?, ?, 0)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, hash);
			pstmt.setString(2, url);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
