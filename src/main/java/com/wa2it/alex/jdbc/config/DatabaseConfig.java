package com.wa2it.alex.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost/SHIPMENT?serverTimezone=UTC";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(MYSQL_DRIVER);

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
