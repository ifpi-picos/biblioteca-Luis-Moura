package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.connection.DatabaseConnection;
import com.biblioteca.model.Usuario;

public class UsuarioDao {

    private final Connection connection;

    public UsuarioDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void create(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, cpf, email) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getCpf());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1)); // Atribui o ID gerado ao objeto
                }
            }
        }
    }

    public ArrayList<Usuario> read() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario readById(int usuarioId) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, usuarioId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario(
                            resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("cpf"),
                            resultSet.getString("email")
                    );
                }
            }
        }

        return usuario;
    }

    public void update(Usuario usuario, int id) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getCpf());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
