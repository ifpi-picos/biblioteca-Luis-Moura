package com.biblioteca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

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
      System.out.println("Erro ao cadastrar livro" + e);
    }
  }

  public ArrayList<Livro> read() {
    ArrayList<Livro> livros = new ArrayList<>();

    String sql = "SELECT * FROM livro";
    try {
      var connection = DatabaseConnection.getConnection();

      var statement = connection.prepareStatement(sql);

      var resultSet = statement.executeQuery();

      while (resultSet.next()) {
        Livro livro = new Livro(resultSet.getString("isbn"), resultSet.getString("autor"),
            resultSet.getString("titulo"), resultSet.getString("editora"), resultSet.getInt("ano"),
            resultSet.getBoolean("emprestado"));

        livros.add(livro);
      }


    } catch (Exception e) {
      System.out.println("Erro ao listar livros" + e);
    }

    return livros;
  }

  public void update(Livro livro) {
    String sql = "UPDATE livro SET autor = ?, titulo = ?, editora = ?, ano = ?, emprestado = ? WHERE isbn = ?";

    try {
      var connection = DatabaseConnection.getConnection();

      var preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, livro.getAutor());
      preparedStatement.setString(2, livro.getTitulo());
      preparedStatement.setString(3, livro.getEditora());
      preparedStatement.setInt(4, livro.getAno());
      preparedStatement.setBoolean(5, livro.getEmprestado());
      preparedStatement.setString(6, livro.getISBN());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
  }
}