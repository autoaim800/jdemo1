package com.billsoft.instore2.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.billsoft.instore2.Dbable;
import com.billsoft.instore2.GenericBuilder;

public class Sqlite3Db implements Dbable {

    private Connection connection;
    private String dbString;

    public Sqlite3Db(String dbFilePathName) {
        try {
            Class.forName("org.sqlite.JDBC");
            dbString = String.format("jdbc:sqlite:%s", dbFilePathName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
    public boolean updatePss(String sql, String[] params) {
        PreparedStatement ps = null;
        boolean ret = false;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setString(i + 1, params[i]);
            }
            // despite the return value
            ps.execute();
            ret = true;
        } catch (SQLException e) {
            StringBuilder sb = new StringBuilder();
            for (String param : params) {
                sb.append(" ").append(param);
            }
            if (19 == e.getErrorCode()) {
                GenericBuilder.error("duplicated row " + sql + sb.toString());
            } else {
                // TODO Auto-generated catch block
                e.printStackTrace();
                GenericBuilder.error("sql exception: " + sql + sb.toString());
            }
        } finally {
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // ignored
                }
                ps = null;
            }
        }
        return ret;
    }

    @Override
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

    @Override
    public String[] queryColumnSs(String sql, int index) {
        GenericBuilder.info("queryColumnSs() " + sql);
        List<String> pending = new ArrayList<String>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pending.add(rs.getString(index));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return GenericBuilder.list2ss(pending);
    }

    @Override
    public boolean existsTable(String tableName) {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet result = meta.getTables(null, null, null, new String[] { "TABLE" });
            while (result.next()) {
                if (tableName.equals(result.getString("TABLE_NAME"))) {
                    return true;
                }
            }
            result.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void createTable(String ddl) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(ddl);
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public int update(String sql) {
        try {
            Statement st = connection.createStatement();
            int afrc = st.executeUpdate(sql);
            st.close();
            return afrc;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -4;
        }
    }

}
