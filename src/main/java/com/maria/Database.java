package com.maria;

import java.io.IOException;
import java.sql.*;

public class Database {
    private static Connection con;
    private static final Database INSTANCE = new Database();

    public static Database getInstance(){
        return INSTANCE;
    }

    private  Database() {
        try {
            String conUrl = new Prefs().getString(Prefs.DB_URL);
            con = DriverManager.getConnection(conUrl);
        } catch (SQLException | IOException e) {
            System.out.printf("Exception reason: %s%n", e.getMessage());
            throw new RuntimeException("Can't create connection.");
        }
    }

    public static int executeUpdate(String query) {
        try (Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.printf("Exception reason: %s%n", e.getMessage());
            throw new RuntimeException("Can't run query.");
        }
    }

    public static ResultSet executeResult(String query) {
        try {
            Statement st = con.createStatement();
            return st.executeQuery(query);
        } catch (SQLException e) {
            System.out.printf("Exception reason: %s%n", e.getMessage());
            throw new RuntimeException("Can't run query.");
        }
    }

    public void connectionClose() {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.printf("Exception reason: %s%n", e.getMessage());
                throw new RuntimeException(e);
            }
    }
}
