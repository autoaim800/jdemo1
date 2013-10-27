package com.billsoft.instore2;

import java.sql.Connection;

public interface Dbable {

    void close();

    /**
     * execute a prepared statement with given array of strings as parameter,
     * sql execute result is ignored, returns true when on exception
     * 
     * @param sql
     *            a string of sql statement
     * @param strings
     *            an array of strings as parameter
     * @return true if no exception, false otherwise
     */
    boolean updatePss(String sql, String[] strings);

    Connection open();

    /**
     * @param sql
     * @param index
     *            an integer of column index, starts from 1
     * @return an array of string
     */
    String[] queryColumnSs(String sql, int index);

    boolean existsTable(String tableName);

    void createTable(String createTable);

    /**
     * execute an update sql, return affected row count
     * 
     * @param sql
     *            a string of sql statement
     * @return an integer of affected row count
     */
    int update(String sql);
}
