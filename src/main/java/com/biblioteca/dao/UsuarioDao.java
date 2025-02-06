package com.biblioteca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.connection.DatabaseConnection;
import com.biblioteca.model.Usuario;

public class UsuarioDao {
  public void create(Usuario usuario) {
    String sql = "INSERT INTO usuario (nome, cpf, email) VALUES (?, ?, ?)";

    try {
      var connection = DatabaseConnection.getConnection();

      var preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, usuario.getNome());
      preparedStatement.setString(2, usuario.getCpf());
      preparedStatement.setString(3, usuario.getEmail());
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Usuario> read() {
    ArrayList<Usuario> usuarios = new ArrayList<>();

    String sql = "SELECT * FROM usuario";
    try {
      var connection = DatabaseConnection.getConnection();

      var preparedStatement = connection.prepareStatement(sql);

      var resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Usuario usuario = new Usuario(resultSet.getString("nome"), resultSet.getString("cpf"),
            resultSet.getString("email"));

        usuarios.add(usuario);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return usuarios;
  }

  public Usuario readById(int usuarioId) {
    Usuario usuario = null;

    String sql = "SELECT * FROM usuario WHERE id = ?";
    try {
      var connection = DatabaseConnection.getConnection();

      var preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, usuarioId);

      var resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        usuario = new Usuario(resultSet.getString("nome"), resultSet.getString("cpf"),
            resultSet.getString("email"));
      }
    } catch (SQLException e) {
      throw  new RuntimeException(e);
    }

    return usuario;
  }

  public void update(Usuario usuario, int id) {
    String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ? WHERE id = ?";

    try {
      var connection = DatabaseConnection.getConnection();

      var preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, usuario.getNome());
      preparedStatement.setString(2, usuario.getCpf());
      preparedStatement.setString(3, usuario.getEmail());
      preparedStatement.setInt(4, id);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void delete(int id) {
    String sql = "DELETE FROM usuario WHERE id = ?";

    try {
      var connection = DatabaseConnection.getConnection();

      var preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}