package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.connection.DatabaseConnection;
import com.biblioteca.model.Livro;

public class LivroDao {

    public void create(Livro livro) {
        String sql = "INSERT INTO livro (isbn, autor, titulo, editora, ano, emprestado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, livro.getISBN());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setString(3, livro.getTitulo());
            preparedStatement.setString(4, livro.getEditora());
            preparedStatement.setInt(5, livro.getAno());
            preparedStatement.setBoolean(6, livro.getEmprestado());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public ArrayList<Livro> read() {
        ArrayList<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Livro livro = new Livro(
                        resultSet.getString("isbn"),
                        resultSet.getString("autor"),
                        resultSet.getString("titulo"),
                        resultSet.getString("editora"),
                        resultSet.getInt("ano"),
                        resultSet.getBoolean("emprestado")
                );
                livros.add(livro);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
        }

        return livros;
    }

    public Livro readByISBN(String ISBN) {
        Livro livro = null;
        String sql = "SELECT * FROM livro WHERE isbn = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, ISBN);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    livro = new Livro(
                            resultSet.getString("isbn"),
                            resultSet.getString("autor"),
                            resultSet.getString("titulo"),
                            resultSet.getString("editora"),
                            resultSet.getInt("ano"),
                            resultSet.getBoolean("emprestado")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro por ISBN: " + e.getMessage());
        }

        return livro;
    }

    public void update(Livro livro) {
        String sql = "UPDATE livro SET autor = ?, titulo = ?, editora = ?, ano = ?, emprestado = ? WHERE isbn = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, livro.getAutor());
            preparedStatement.setString(2, livro.getTitulo());
            preparedStatement.setString(3, livro.getEditora());
            preparedStatement.setInt(4, livro.getAno());
            preparedStatement.setBoolean(5, livro.getEmprestado());
            preparedStatement.setString(6, livro.getISBN());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    public void delete(String isbn) {
        String sql = "DELETE FROM livro WHERE isbn = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, isbn);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar livro: " + e.getMessage());
        }
    }
}
