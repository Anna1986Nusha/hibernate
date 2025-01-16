package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Veronika2009!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
