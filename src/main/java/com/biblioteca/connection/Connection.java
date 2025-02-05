package com.biblioteca.connection;

import java.sql.DriverManager;

public class Connection {
  private static final String URL = "jdbc:postgresql://localhost:5433/banco";
  private static final String USER = "usuario";
  private static final String PASS = "senha123";

  private static java.sql.Connection connection;

  public static java.sql.Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(URL, USER, PASS);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return connection;
  }
}
