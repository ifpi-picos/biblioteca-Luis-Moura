package com.biblioteca;

import java.util.Scanner;

import com.biblioteca.controller.LivroController;
import com.biblioteca.controller.UsuarioController;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    UsuarioController usuarioController = new UsuarioController();
    LivroController livroController = new LivroController();

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
      System.out.println("5. Cadastrar livro");
      System.out.println("6. Listar todos os livros");
      System.out.println("7. Atualizar livro");
      System.out.println("8. Deletar livro");
      System.out.println("==============================");
      System.out.print("Escolha uma opção: ");

      int opcao = scanner.nextInt();
      scanner.nextLine(); // Consumir a nova linha

      String nomeUsuario, cpfUsuario, emailUsuario, ISBN, autor, titulo, editora;

      switch (opcao) {
        case 1:
          System.out.println("\n=== Cadastrar Usuário ===");
          System.out.print("Digite o nome do usuário: ");
          nomeUsuario = scanner.nextLine();
          System.out.print("Digite o CPF do usuário: ");
          cpfUsuario = scanner.nextLine();
          System.out.print("Digite o email do usuário: ");
          emailUsuario = scanner.nextLine();

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

        case 5:
          System.out.println("\n=== Cadastrar Livro ===");
          System.out.println("Digite o ISBN do livro: ");
          ISBN = scanner.nextLine();
          System.out.println("Digite o nome do autor: ");
          autor = scanner.nextLine();
          System.out.println("Digite o titulo do livro: ");
          titulo = scanner.nextLine();
          System.out.println("Digite o nome da editora: ");
          editora = scanner.nextLine();
          System.out.println("Digite o ano de lançamento do livro: ");
          int ano = scanner.nextInt();
          scanner.nextLine();

          Livro novoLivro = new Livro(ISBN, autor, titulo, editora, ano, false);

          livroController.cadastrarLivro(novoLivro);
          System.out.println("\nLivro cadastrado com sucesso!");

          break;

        case 6:
            System.out.println("\n=== Todos os Livros ===");
            livroController.listarLivros();
            break;

        case 7:
            System.out.println("\n=== Atualizar Livro ===");
            System.out.print("Digite o ISBN do livro a ser atualizado: ");
            ISBN = scanner.nextLine();
            System.out.print("Digite o novo nome do autor: ");
            autor = scanner.nextLine();
            System.out.print("Digite o novo titulo do livro: ");
            titulo = scanner.nextLine();
            System.out.print("Digite o novo nome da editora: ");
            editora = scanner.nextLine();
            System.out.print("Digite o novo ano de lançamento do livro: ");
            ano = scanner.nextInt();
            scanner.nextLine();

            Livro livroAtualizado = new Livro(ISBN, autor, titulo, editora, ano, false);

            livroController.atualizarLivro(livroAtualizado);
            System.out.println("\nLivro atualizado com sucesso!");
            break;

        case 8:
            System.out.println("\n=== Deletar Livro ===");
            System.out.print("Digite o ISBN do livro a ser deletado: ");
            ISBN = scanner.nextLine();

            livroController.deletarLivro(ISBN);
            System.out.println("\nLivro deletado com sucesso!");
            break;

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