package com.biblioteca.controller;

import com.biblioteca.dao.LivroDao;
import com.biblioteca.model.Livro;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivroController {
    private final LivroDao livroDao;
    private final Connection connection;

    public LivroController(Connection connection) throws SQLException {
        this.connection = connection;
        this.livroDao = new LivroDao(connection);
    }

    public void cadastrarLivro(Livro livro) throws SQLException {
        livroDao.create(livro);
    }

    public void listarLivros() throws SQLException {
        ArrayList<Livro> livros = livroDao.read();
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("ISBN: " + livro.getISBN());
            System.out.println("------------------------------");
        }
    }

    public void listarLivrosEmprestados() throws SQLException {
        ArrayList<Livro> livros = livroDao.readLivrosEmprestados();
        System.out.println("\n=== Livros Emprestados ===");
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("ISBN: " + livro.getISBN());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("--------------------------");
        }
    }

    public void listarLivrosDisponiveis() throws SQLException {
        ArrayList<Livro> livros = livroDao.readLivrosDisponiveis();
        System.out.println("\n=== Livros Disponíveis ===");
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("ISBN: " + livro.getISBN());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("--------------------------");
        }
    }

    public Livro listarLivroPorISBN(String ISBN) throws SQLException {
        return livroDao.readByISBN(ISBN);
    }

    public void atualizarLivro(Livro livro) throws SQLException {
        livroDao.update(livro);
    }

    public void deletarLivro(String isbn) throws SQLException {
        livroDao.delete(isbn);
    }
}
