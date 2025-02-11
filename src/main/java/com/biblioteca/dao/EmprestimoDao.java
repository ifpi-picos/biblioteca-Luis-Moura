package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.connection.DatabaseConnection;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;

public class EmprestimoDao {
    public void create(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (data_emprestimo, data_devolucao, usuario_id, livro_isbn) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Converte java.util.Date para java.sql.Date
            preparedStatement.setDate(1, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            preparedStatement.setInt(3, emprestimo.getUsuario().getId());
            preparedStatement.setString(4, emprestimo.getLivro().getISBN());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar empréstimo: " + e.getMessage());
        }
    }

    public ArrayList<Emprestimo> readAll() {
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            UsuarioDao usuarioDao = new UsuarioDao();
            LivroDao livroDao = new LivroDao();

            while (resultSet.next()) {
                java.sql.Date dataEmprestimoSql = resultSet.getDate("data_emprestimo");
                java.sql.Date dataDevolucaoSql = resultSet.getDate("data_devolucao");
                int usuarioId = resultSet.getInt("usuario_id");
                String livroIsbn = resultSet.getString("livro_isbn");

                Usuario usuario = usuarioDao.readById(usuarioId);
                Livro livro = livroDao.readByISBN(livroIsbn);

                Emprestimo emprestimo = new Emprestimo(
                        new java.util.Date(dataEmprestimoSql.getTime()),
                        new java.util.Date(dataDevolucaoSql.getTime()),
                        usuario,
                        livro);
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return emprestimos;
    }
}
