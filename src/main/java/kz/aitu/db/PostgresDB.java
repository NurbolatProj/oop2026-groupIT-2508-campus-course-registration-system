package kz.aitu.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB implements IDB {

    @Override 
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/asik3_db",
                    "postgres",
                    "Marsel2007."
            );
        } catch (Exception e) {
            e.printStackTrace(); // 👈 ОБЯЗАТЕЛЬНО
            throw new RuntimeException("Database connection failed");
        }
    }
}
