package com.gn.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcModel {
    /**
     * Phương thức kết nối tới database
     *
     * @return Connection
     */
    public Connection getConnectionOf() {
        Connection objConn = null;
        String sConnURL = null;
        JdbcObject objEntity = new JdbcObject(
                DatabaseConfig.SERVER_IP,
                DatabaseConfig.SERVER_USER_NAME,
                DatabaseConfig.SERVER_PWD,
                DatabaseConfig.SERVER_DATABASE_NAME,
                DatabaseConfig.SERVER_PORT);
        try {
            Class.forName(objEntity.getsClass());
            sConnURL = "jdbc:mysql://"
                    + objEntity.getsServerIP() + ":" + objEntity.getsPort()
                    + "/" + objEntity.getsDatabase();
            objConn = DriverManager.getConnection(sConnURL, objEntity.getsUserId(), objEntity.getsPwd());
        } catch (Exception se) {
            se.printStackTrace();
        }
        return objConn;
    }
}
