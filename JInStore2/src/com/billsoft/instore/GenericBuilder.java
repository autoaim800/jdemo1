package com.billsoft.instore;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericBuilder {

	public static void info(String s) {
		System.err.println(String.format("info: %s", s));
	}

	public static void error(String s) {
		System.err.println(String.format("error: %s", s));
	}

	protected boolean createTable(java.sql.Connection connection, String ddl) {
		info("create table");
		try {
			Statement stmt = connection.createStatement();
			boolean b = stmt.execute(ddl);
			stmt.close();
			return b;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	protected String sha1(String raw) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(raw.getBytes());
			return byteArrayToHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String byteArrayToHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	protected void forceExistsTable(java.sql.Connection connection,
			String tableName, String tableDdl) {
		try {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet result = meta.getTables(null, null, null,
					new String[] { "TABLE" });
			while (result.next()) {
				if (tableName.equals(result.getString("TABLE_NAME"))) {
					info("table exists");
					return;
				}
			}
			result.close();
			createTable(connection, tableDdl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
