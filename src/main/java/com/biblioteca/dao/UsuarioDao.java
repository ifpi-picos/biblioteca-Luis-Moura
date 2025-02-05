package com.biblioteca.dao;

import com.biblioteca.model.Usuario;
import com.biblioteca.connection.DatabaseConnection;

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
}