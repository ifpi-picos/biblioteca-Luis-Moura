package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
  private final List<Livro> livros;
  private final List<Emprestimo> emprestimos;

  public Biblioteca() {
    this.livros = new ArrayList<Livro>();
    this.emprestimos = new ArrayList<Emprestimo>();
  }

  public void cadastrarLivro(Livro livro) {
    livros.add(livro);
  }

  public List<Livro> listarLivros() {
    return livros;
  }

  public Livro listarLivroPorISBN(String ISBN) {
    for (int i = 0; i < livros.size(); i++) {
      if (livros.get(i).getISBN() == ISBN) {
        return livros.get(i);
      }
    }

    return null;
  }
  
  public List<Livro> listarLivrosEmprestados() {
    final List<Livro> livrosEmprestados = new ArrayList<Livro>();

    for (int i = 0; i < livros.size(); i++) {
      if (livros.get(i).getEmprestado() == true) {
        livrosEmprestados.add(livros.get(i));
      }
    }

    if (livrosEmprestados.size() > 0) {
      return livrosEmprestados;
    }

    return null;
  }

  public List<Livro> listarLivrosDisponiveis() {
    final List<Livro> livrosDisponiveis = new ArrayList<Livro>();

    for (int i = 0; i < livros.size(); i++) {
      if (livros.get(i).getEmprestado() == false) {
        livrosDisponiveis.add(livros.get(i));
      }
    }

    if (livrosDisponiveis.size() > 0) {
      return livrosDisponiveis;
    }

    return null;
  }

  public List<Emprestimo> listarHistoricoDeEmprestimosDoUsuario(Usuario usuario) {
    final List<Emprestimo> historicoDeEmprestimos = new ArrayList<Emprestimo>();

    for (int i = 0; i < emprestimos.size(); i++) {
      if (emprestimos.get(i).getUsuario() == usuario) {
        historicoDeEmprestimos.add(emprestimos.get(i));
      }
    }

    return historicoDeEmprestimos;
  }

  public void pegarLivroEmprestado(Usuario usuario, Livro livro) {
    if (livro.getEmprestado() == false) {
      LocalDate dataEmprestimo = LocalDate.now();
      LocalDate dataDevolucao = dataEmprestimo.plusWeeks(4);

      Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, usuario, livro);

      emprestimos.add(emprestimo);
      usuario.adicionarEmprestimo(emprestimo);
      livro.setEmprestado(true);
    }
    
    System.out.println("\nEste livro já esta emprestado!\n");
  }
}
