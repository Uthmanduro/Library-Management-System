package utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
//    private static final String url = "jdbc:mysql://localhost:3306/dreamdevs";
    static final String url = "jdbc:mysql://localhost:3306/druth";

    private static final String user = "root";
    private static final String password = "0000";

    public static Connection connectToDb() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}