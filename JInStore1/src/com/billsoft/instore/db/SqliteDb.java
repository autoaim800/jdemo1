package com.billsoft.instore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDb {

	private String dbString = null;
	private Connection connection;

	public SqliteDb(String dbFileName) {
		try {
			Class.forName("org.sqlite.JDBC");
			dbString = String.format("jdbc:sqlite:%s", dbFileName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() {
		if (null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = null;
		}
	}
	

	public Connection open() {
		if (null == dbString) {
			return null;
		}
		try {
			connection = DriverManager.getConnection(dbString);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
