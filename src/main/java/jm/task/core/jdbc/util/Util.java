package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    private static final String PASSWORD = "root";
    private static final String USER = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/new_test";

    private Util() {
    }

    public static Connection open() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    // реализуйте настройку соеденения с БД
}
