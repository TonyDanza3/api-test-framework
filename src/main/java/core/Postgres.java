package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utils.Config.*;

public class Postgres {
    public static Connection getDbConnection() {
        try {
            return DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Could not connect to database " + POSTGRES_URL + ": " + e);
        }
    }
}
