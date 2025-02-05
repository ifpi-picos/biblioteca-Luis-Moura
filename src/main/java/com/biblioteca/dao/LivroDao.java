package com.biblioteca.dao;

import com.biblioteca.connection.DatabaseConnection;
import com.biblioteca.model.Livro;

public class LivroDao {
  public void create(Livro livro) {
    String sql = "INSERT INTO livro (isbn, autor, titulo, editora, ano, emprestado) values (?, ?, ?, ?, ?, ?)";

    try {
      var connection = DatabaseConnection.getConnection();

      var preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, livro.getISBN());
      preparedStatement.setString(2, livro.getAutor());
      preparedStatement.setString(3, livro.getTitulo());
      preparedStatement.setString(4, livro.getEditora());
      preparedStatement.setInt(5, livro.getAno());
      preparedStatement.setBoolean(6, livro.getEmprestado());
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}