package com.biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {
  private final LocalDate dataEmprestimo;
  private final LocalDate dataDevolucao;
  private final Usuario usuario;
  private final Livro livro;

  public Emprestimo(LocalDate dataEmprestimo, LocalDate dataDevolucao, Usuario usuario, Livro livro) {
    if (dataEmprestimo == null || dataDevolucao == null || usuario == null || livro == null) {
      throw new IllegalArgumentException("Nenhum parâmetro pode ser nulo");
    }
    this.dataEmprestimo = dataEmprestimo;
    this.dataDevolucao = dataDevolucao;
    this.usuario = usuario;
    this.livro = livro;
  }

  public LocalDate getDataEmprestimo() {
    return dataEmprestimo;
  }

  public LocalDate getDataDevolucao() {
    return dataDevolucao;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public Livro getLivro() {
    return livro;
  }
}
