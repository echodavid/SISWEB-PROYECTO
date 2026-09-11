package org.uv.util;

public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/pr07_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private DatabaseConfig() {
    }

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
