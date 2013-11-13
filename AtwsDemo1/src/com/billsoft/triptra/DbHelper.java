package com.billsoft.triptra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    private static boolean loaded = false;

    public static Connection obtainConnection() {
        try {
            if (!loaded) {
                Class.forName("com.mysql.jdbc.Driver");
                loaded = true;
            }
            return DriverManager.getConnection("jdbc:mysql://localhost/atws", "root", "root");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
