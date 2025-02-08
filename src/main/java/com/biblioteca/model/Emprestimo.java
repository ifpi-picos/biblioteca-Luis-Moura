package com.biblioteca.model;

import java.time.LocalDate;
import java.util.Date;

public class Emprestimo {
  private final Date dataEmprestimo;
  private final Date dataDevolucao;
  private final Usuario usuario;
  private final Livro livro;

  public Emprestimo(Date dataEmprestimo, Date dataDevolucao, Usuario usuario, Livro livro) {
    if (dataEmprestimo == null || dataDevolucao == null || usuario == null || livro == null) {
      throw new IllegalArgumentException("Nenhum parâmetro pode ser nulo");
    }
    this.dataEmprestimo = dataEmprestimo;
    this.dataDevolucao = dataDevolucao;
    this.usuario = usuario;
    this.livro = livro;
  }

  public Date getDataEmprestimo() {
    return dataEmprestimo;
  }

  public Date getDataDevolucao() {
    return dataDevolucao;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public Livro getLivro() {
    return livro;
  }
}
