package com.mylierp.general.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {

    private static String connString = "jdbc:mysql://localhost:3306/mylitest";
    private static String username = "root";
    private static String password = "root";
    private static Connection connection;

    static {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(connString, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
//    //ODBC
//    private static String connString = "jdbc:odbc:mylierp";
//
//    private static Connection connection;
//
//    public static void main(String[] args) {
//        // static {
//        try {
//            DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
//            connection = DriverManager.getConnection("jdbc:odbc:mylierp");
//            System.out.println("Connected");
//        } catch (SQLException ex) {
//            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //  }
//
//    }
//
//    public static Connection getConnection() {
//        return connection;
//    }
}
