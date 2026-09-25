package org.uv.util;

public class DatabaseConfig {
    private DatabaseConfig() {
    }

    public static String getUrl() {
        return System.getProperty("db.url", System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/pr07_db"));
    }

    public static String getUser() {
        return System.getProperty("db.user", System.getenv().getOrDefault("DB_USER", "postgres"));
    }

    public static String getPassword() {
        return System.getProperty("db.password", System.getenv().getOrDefault("DB_PASSWORD", "postgres"));
    }
}
