package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.connection.DatabaseConnection;
import com.biblioteca.model.Livro;

public class LivroDao {

    private Connection connection;

    public LivroDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Livro livro) throws SQLException {
        String sql = "INSERT INTO livro (isbn, autor, titulo, editora, ano, emprestado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, livro.getISBN());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setString(3, livro.getTitulo());
            preparedStatement.setString(4, livro.getEditora());
            preparedStatement.setInt(5, livro.getAno());
            preparedStatement.setBoolean(6, livro.getEmprestado());
            preparedStatement.executeUpdate();

        }
    }

    public ArrayList<Livro> read() throws SQLException {
        ArrayList<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";

        try (PreparedStatement statement = connection.prepareStatement(sql);
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
        }

        return livros;
    }

    public Livro readByISBN(String ISBN) throws SQLException {
        Livro livro = null;
        String sql = "SELECT * FROM livro WHERE isbn = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

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

        }

        return livro;
    }

    public ArrayList<Livro> readLivrosEmprestados() throws SQLException {
        ArrayList<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE emprestado = true";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Livro livro = new Livro(
                        resultSet.getString("isbn"),
                        resultSet.getString("autor"),
                        resultSet.getString("titulo"),
                        resultSet.getString("editora"),
                        resultSet.getInt("ano"),
                        true);
                livros.add(livro);
            }
        }

        return livros;
    }

    public ArrayList<Livro> readLivrosDisponiveis() throws SQLException {
        ArrayList<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE emprestado = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Livro livro = new Livro(
                        resultSet.getString("isbn"),
                        resultSet.getString("autor"),
                        resultSet.getString("titulo"),
                        resultSet.getString("editora"),
                        resultSet.getInt("ano"),
                        false);
                livros.add(livro);
            }
        }

        return livros;
    }

    public void update(Livro livro) throws SQLException {
        String sql = "UPDATE livro SET autor = ?, titulo = ?, editora = ?, ano = ?, emprestado = ? WHERE isbn = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, livro.getAutor());
            preparedStatement.setString(2, livro.getTitulo());
            preparedStatement.setString(3, livro.getEditora());
            preparedStatement.setInt(4, livro.getAno());
            preparedStatement.setBoolean(5, livro.getEmprestado());
            preparedStatement.setString(6, livro.getISBN());
            preparedStatement.executeUpdate();

        }
    }

    public void delete(String isbn) throws SQLException {
        String sql = "DELETE FROM livro WHERE isbn = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, isbn);
            preparedStatement.executeUpdate();

        }
    }
}
