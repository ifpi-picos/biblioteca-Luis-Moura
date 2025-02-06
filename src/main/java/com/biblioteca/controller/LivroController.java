package com.biblioteca.controller;

import com.biblioteca.dao.LivroDao;
import com.biblioteca.model.Livro;

import java.util.ArrayList;

public class LivroController {
  private final LivroDao livroDao;

  public LivroController() {
    this.livroDao = new LivroDao();
  }

  public void cadastrarLivro(Livro livro) {
    livroDao.create(livro);
  }

  public void listarLivros() {
    ArrayList<Livro> livros = livroDao.read();
    for (Livro livro: livros) {
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Editora: " + livro.getEditora());
        System.out.println("ISBN: " + livro.getISBN());
        System.out.println();
    }
  }

  public void atualizarLivro(Livro livro) {
    livroDao.update(livro);
  }

  public void deletarLivro(String isbn) {
    livroDao.delete(isbn);
  }
}
