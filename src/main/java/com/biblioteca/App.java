package com.biblioteca;

import java.util.Scanner;

import com.biblioteca.controller.UsuarioController;
import com.biblioteca.model.Usuario;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    UsuarioController usuarioController = new UsuarioController();

    // Menu de opções
    while (true) {
      System.out.println("\n==============================");
      System.out.println("       Biblioteca Menu        ");
      System.out.println("==============================");
      System.out.println("0. Sair do sistema");
      System.out.println("1. Cadastrar usuário");
      System.out.println("2. Listar todos os usuários");
      System.out.println("3. Atualizar usuário");
      System.out.println("4. Deletar usuário");
      System.out.println("==============================");
      System.out.print("Escolha uma opção: ");

      int opcao = scanner.nextInt();
      scanner.nextLine(); // Consumir a nova linha

      switch (opcao) {
        case 1:
          System.out.println("\n=== Cadastrar Usuário ===");
          System.out.print("Digite o nome do usuário: ");
          String nomeUsuario = scanner.nextLine();
          System.out.print("Digite o CPF do usuário: ");
          String cpfUsuario = scanner.nextLine();
          System.out.print("Digite o email do usuário: ");
          String emailUsuario = scanner.nextLine();

          Usuario novoUsuario = new Usuario(nomeUsuario, cpfUsuario, emailUsuario);
          usuarioController.cadastrarUsuario(novoUsuario);
          System.out.println("\nUsuário cadastrado com sucesso!");
          break;

        case 2:
          System.out.println("\n=== Todos os Usuários ===");
          usuarioController.listarUsuarios();
          break;

        case 3:
          System.out.println("\n=== Atualizar Usuário ===");

          System.out.print("Digite o ID do usuário a ser atualizado: ");
          int idUsuario = scanner.nextInt();
          scanner.nextLine(); // Consumir a nova linha

          System.out.print("Digite o novo nome do usuário: ");
          nomeUsuario = scanner.nextLine();
          System.out.print("Digite o novo CPF do usuário: ");
          cpfUsuario = scanner.nextLine();
          System.out.print("Digite o novo email do usuário: ");
          emailUsuario = scanner.nextLine();

          Usuario usuarioAtualizado = new Usuario(nomeUsuario, cpfUsuario, emailUsuario);

          usuarioController.atualizarUsuario(usuarioAtualizado, idUsuario);
          System.out.println("\nUsuário atualizado com sucesso!");
          break;

        case 4:
          System.out.println("\n=== Deletar Usuário ===");
          System.out.print("Digite o ID do usuário a ser deletado: ");
          int id = scanner.nextInt();
          scanner.nextLine(); // Consumir a nova linha

          usuarioController.deletarUsuario(id);
          System.out.println("\nUsuário deletado com sucesso!");
          break;

        // case 1:
        // // Cadastrar livro
        // System.out.println("\n=== Cadastrar Livro ===");
        // System.out.print("Digite o ISBN do livro: ");
        // String isbnLivro = scanner.nextLine();
        // System.out.print("Digite o autor do livro: ");
        // String autorLivro = scanner.nextLine();
        // System.out.print("Digite o título do livro: ");
        // String tituloLivro = scanner.nextLine();
        // System.out.print("Digite a editora do livro: ");
        // String editoraLivro = scanner.nextLine();
        // System.out.print("Digite o ano de publicação do livro: ");
        // int anoLivro = scanner.nextInt();
        // scanner.nextLine();

        // Livro livro = new Livro(isbnLivro, autorLivro, tituloLivro, editoraLivro,
        // anoLivro, false);
        // biblioteca.cadastrarLivro(livro);
        // System.out.println("\nLivro cadastrado com sucesso!");
        // break;

        // case 2:
        // // Listar todos os livros
        // System.out.println("\n=== Todos os Livros ===");
        // List<Livro> todosLivros = biblioteca.listarLivros();
        // for (Livro l : todosLivros) {
        // System.out.println(l.getTitulo());
        // }
        // break;

        // case 3:
        // // Buscar livro por ISBN
        // System.out.println("\n=== Buscar Livro por ISBN ===");
        // System.out.print("Digite o ISBN do livro: ");
        // isbnLivro = scanner.nextLine();
        // livro = biblioteca.listarLivroPorISBN(isbnLivro);

        // if (livro != null) {
        // System.out.println("\nLivro encontrado: " + livro.getTitulo() + " por " +
        // livro.getAutor());
        // } else {
        // System.out.println("\nLivro não encontrado.");
        // }
        // break;

        // case 4:
        // // Listar livros emprestados
        // System.out.println("\n=== Livros Emprestados ===");
        // List<Livro> livrosEmprestados = biblioteca.listarLivrosEmprestados();
        // if (livrosEmprestados != null) {
        // for (Livro l : livrosEmprestados) {
        // System.out.println(l.getTitulo());
        // }
        // } else {
        // System.out.println("\nNenhum livro emprestado.");
        // }
        // break;

        // case 5:
        // // Listar livros disponíveis
        // System.out.println("\n=== Livros Disponíveis ===");
        // List<Livro> livrosDisponiveis = biblioteca.listarLivrosDisponiveis();
        // if (livrosDisponiveis != null) {
        // for (Livro l : livrosDisponiveis) {
        // System.out.println(l.getTitulo() + " | " + l.getAno());
        // }
        // } else {
        // System.out.println("\nNenhum livro disponível.");
        // }
        // break;

        // case 6:
        // // Listar histórico de empréstimos do usuário
        // System.out.println("\n=== Histórico de Empréstimos ===");
        // System.out.print("Digite o nome do usuário: ");
        // String nomeUsuario = scanner.nextLine();
        // System.out.print("Digite o CPF do usuário: ");
        // String cpfUsuario = scanner.nextLine();
        // System.out.print("Digite o email do usuário: ");
        // String emailUsuario = scanner.nextLine();

        // Usuario usuario = new Usuario(nomeUsuario, cpfUsuario, emailUsuario);
        // List<Emprestimo> historicoEmprestimos =
        // biblioteca.listarHistoricoDeEmprestimosDoUsuario(usuario);
        // for (Emprestimo e : historicoEmprestimos) {
        // System.out.println("Livro: " + e.getLivro().getTitulo() + ", Data de
        // Empréstimo: " + e.getDataEmprestimo()
        // + ", Data de Devolução: " + e.getDataDevolucao());
        // }
        // break;

        // case 7:
        // // Pegar livro emprestado
        // System.out.println("\n=== Pegar Livro Emprestado ===");
        // System.out.print("Digite o nome do usuário: ");
        // nomeUsuario = scanner.nextLine();
        // System.out.print("Digite o CPF do usuário: ");
        // cpfUsuario = scanner.nextLine();
        // System.out.print("Digite o email do usuário: ");
        // emailUsuario = scanner.nextLine();

        // usuario = new Usuario(nomeUsuario, cpfUsuario, emailUsuario);

        // System.out.print("Digite o ISBN do livro: ");
        // isbnLivro = scanner.nextLine();
        // livro = biblioteca.listarLivroPorISBN(isbnLivro);

        // if (livro != null) {
        // biblioteca.pegarLivroEmprestado(usuario, livro);

        // System.out.println("\nLivro pego");
        // } else {
        // System.out.println("\nLivro não encontrado.");
        // }
        // break;

        // case 8:
        // // Devolver livro
        // System.out.println("\n=== Devolver Livro ===");
        // System.out.print("Digite o ISBN do livro: ");
        // isbnLivro = scanner.nextLine();
        // livro = biblioteca.listarLivroPorISBN(isbnLivro);

        // if (livro != null) {
        // livro.setEmprestado(false);
        // System.out.println("\nLivro devolvido com sucesso!");
        // } else {
        // System.out.println("\nLivro não encontrado.");
        // }
        // break;

        case 0:
          // Sair
          System.out.println("\nSaindo...");
          scanner.close();
          return;

        default:
          System.out.println("\nOpção inválida. Tente novamente.");
      }
    }
  }
}