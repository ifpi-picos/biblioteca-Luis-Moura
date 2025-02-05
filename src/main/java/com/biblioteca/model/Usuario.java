package com.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
  private final String nome;
  private final String cpf;
  private final String email;
  private final List<Emprestimo> historicoEmprestimos;

  public Usuario(String nome, String cpf, String email) {
    if (nome == null || cpf == null || email == null) {
      throw new IllegalArgumentException("Nenhum parâmetro pode ser nulo");
    }
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.historicoEmprestimos = new ArrayList<Emprestimo>();
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public String getEmail() {
    return email;
  }

  public List<Emprestimo> getHistoricoEmprestimos() {
    return historicoEmprestimos;
  }

  public void adicionarEmprestimo(Emprestimo emprestimo) {
    historicoEmprestimos.add(emprestimo);
  }
}
