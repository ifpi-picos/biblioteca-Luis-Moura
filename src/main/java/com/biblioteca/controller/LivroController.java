package com.biblioteca.controller;

import com.biblioteca.dao.LivroDao;
import com.biblioteca.model.Livro;

public class LivroController {
  private final LivroDao livroDao;

  public LivroController() {
    this.livroDao = new LivroDao();
  }

  public void cadastrarLivro(Livro livro) {
    livroDao.create(livro);
  }
}
