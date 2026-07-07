package com.example.jwt;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabaseTest {

    @Test
    public void executeDelete() {
        String url = "jdbc:mysql://localhost:3306/jwt_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "mysql@123RJG";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to MySQL database jwt_db...");
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement stmt = conn.createStatement()) {
                System.out.println("Deleting duplicate user: 3200584@dsengg.ac.in...");
                int rows = stmt.executeUpdate("DELETE FROM _user WHERE email = '3200584@dsengg.ac.in';");
                System.out.println("Deleted " + rows + " rows successfully!");
            }
        } catch (Exception e) {
            System.err.println("Error executing SQL delete: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
