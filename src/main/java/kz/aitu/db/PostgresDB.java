package kz.aitu.db;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresDB implements IDB {

    @Override
    public Connection getConnection() {
        try {
            return DatabaseConnection
                    .getInstance()
                    .getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }
}
