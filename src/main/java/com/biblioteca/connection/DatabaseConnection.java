package com.biblioteca.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String URL = "jdbc:postgresql://localhost:5433/banco";
  private static final String USER = "usuario";
  private static final String PASSWORD = "senha123";

  private static Connection connection;

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return connection;
  }
}