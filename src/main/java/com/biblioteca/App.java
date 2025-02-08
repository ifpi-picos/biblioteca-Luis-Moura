package com.biblioteca;

import java.util.Scanner;

import com.biblioteca.controller.LivroController;
import com.biblioteca.controller.UsuarioController;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioController usuarioController = new UsuarioController();
    private static final LivroController livroController = new LivroController();

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    listarUsuarioPorId();
                    break;
                case 4:
                    atualizarUsuario();
                    break;
                case 5:
                    deletarUsuario();
                    break;
                case 6:
                    cadastrarLivro();
                    break;
                case 7:
                    listarLivros();
                    break;
                case 8:
                    listarLivroPorISBN();
                    break;
                case 9:
                    atualizarLivro();
                    break;
                case 10:
                    deletarLivro();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n==============================");
        System.out.println("       Biblioteca Menu        ");
        System.out.println("==============================");
        System.out.println("0. Sair do sistema");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Listar todos os usuários");
        System.out.println("3. Listar usuário por ID");
        System.out.println("4. Atualizar usuário");
        System.out.println("5. Deletar usuário");
        System.out.println("6. Cadastrar livro");
        System.out.println("7. Listar todos os livros");
        System.out.println("8. Listar livro por ISBN");
        System.out.println("9. Atualizar livro");
        System.out.println("10. Deletar livro");
        System.out.println("==============================");
    }

    private static int lerInteiro(String mensagem) {
        int numero = -1;
        while (true) {
            try {
                System.out.print(mensagem);
                numero = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            }
        }
        return numero;
    }

    private static String lerLinha(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static void cadastrarUsuario() {
        System.out.println("\n=== Cadastrar Usuário ===");
        String nome = lerLinha("Digite o nome do usuário: ");
        String cpf = lerLinha("Digite o CPF do usuário: ");
        String email = lerLinha("Digite o email do usuário: ");
        Usuario novoUsuario = new Usuario(nome, cpf, email);
        usuarioController.cadastrarUsuario(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void listarUsuarios() {
        System.out.println("\n=== Todos os Usuários ===");
        usuarioController.listarUsuarios();
    }

    private static void listarUsuarioPorId() {
        System.out.println("\n=== Listar Usuário por ID ===");
        int id = lerInteiro("Digite o ID do usuário: ");
        Usuario usuario = usuarioController.listarUsuarioPorId(id);
        if (usuario != null) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Email: " + usuario.getEmail());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void atualizarUsuario() {
        System.out.println("\n=== Atualizar Usuário ===");
        int id = lerInteiro("Digite o ID do usuário a ser atualizado: ");
        String nome = lerLinha("Digite o novo nome do usuário: ");
        String cpf = lerLinha("Digite o novo CPF do usuário: ");
        String email = lerLinha("Digite o novo email do usuário: ");
        Usuario usuarioAtualizado = new Usuario(nome, cpf, email);
        usuarioController.atualizarUsuario(usuarioAtualizado, id);
        System.out.println("Usuário atualizado com sucesso!");
    }

    private static void deletarUsuario() {
        System.out.println("\n=== Deletar Usuário ===");
        int id = lerInteiro("Digite o ID do usuário a ser deletado: ");
        usuarioController.deletarUsuario(id);
        System.out.println("Usuário deletado com sucesso!");
    }

    private static void cadastrarLivro() {
        System.out.println("\n=== Cadastrar Livro ===");
        String isbn = lerLinha("Digite o ISBN do livro: ");
        String autor = lerLinha("Digite o nome do autor: ");
        String titulo = lerLinha("Digite o título do livro: ");
        String editora = lerLinha("Digite o nome da editora: ");
        int ano = lerInteiro("Digite o ano de lançamento do livro: ");
        Livro novoLivro = new Livro(isbn, autor, titulo, editora, ano, false);
        livroController.cadastrarLivro(novoLivro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void listarLivros() {
        System.out.println("\n=== Todos os Livros ===");
        livroController.listarLivros();
    }

    private static void listarLivroPorISBN() {
        System.out.println("\n=== Listar Livro por ISBN ===");
        String isbn = lerLinha("Digite o ISBN do livro: ");
        Livro livro = livroController.listarLivroPorISBN(isbn);
        if (livro != null) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("ISBN: " + livro.getISBN());
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void atualizarLivro() {
        System.out.println("\n=== Atualizar Livro ===");
        String isbn = lerLinha("Digite o ISBN do livro a ser atualizado: ");
        String autor = lerLinha("Digite o novo nome do autor: ");
        String titulo = lerLinha("Digite o novo título do livro: ");
        String editora = lerLinha("Digite o novo nome da editora: ");
        int ano = lerInteiro("Digite o novo ano de lançamento do livro: ");
        Livro livroAtualizado = new Livro(isbn, autor, titulo, editora, ano, false);
        livroController.atualizarLivro(livroAtualizado);
        System.out.println("Livro atualizado com sucesso!");
    }

    private static void deletarLivro() {
        System.out.println("\n=== Deletar Livro ===");
        String isbn = lerLinha("Digite o ISBN do livro a ser deletado: ");
        livroController.deletarLivro(isbn);
        System.out.println("Livro deletado com sucesso!");
    }
}
