package com.biblioteca;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.biblioteca.connection.DatabaseConnection;
import com.biblioteca.controller.EmprestimoController;
import com.biblioteca.controller.LivroController;
import com.biblioteca.controller.UsuarioController;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;

//tirar a duvida com jesiel sobre o por que não poder deletar um usuário depois dele já fazer o emprestimo

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static UsuarioController usuarioController;
    private static LivroController livroController;
    private static EmprestimoController emprestimoController;

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            usuarioController = new UsuarioController(connection);
            livroController = new LivroController(connection);
            emprestimoController = new EmprestimoController(connection);

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
                    case 11:
                        realizarEmprestimo();
                        break;
                    case 12:
                        listarLivrosEmprestados();
                        break;
                    case 13:
                        listarLivrosDisponiveis();
                        break;
                    case 14:
                        devolverLivro();
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            e.printStackTrace();
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
        System.out.println("11. Realizar empréstimo");
        System.out.println("12. Listar livros emprestados");
        System.out.println("13. Listar livros disponíveis");
        System.out.println("14. Devolver livro");
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

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
            System.out.println("\nPreencha todos os campos!");
            return;
        }

        if (!cpf.matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")) {
            System.out.println("\nCPF inválido!");
            return;
        }

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("\nEmail inválido!");
            return;
        }

        Usuario novoUsuario = new Usuario(null, nome, cpf, email);

        try {
            usuarioController.cadastrarUsuario(novoUsuario);
            System.out.println("\nUsuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarUsuarios() {
        System.out.println("\n=== Todos os Usuários ===");
        try {
            usuarioController.listarUsuarios();
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarUsuarioPorId() {
        System.out.println("\n=== Listar Usuário por ID ===");
        int id = lerInteiro("Digite o ID do usuário: ");

        try {
            Usuario usuario = usuarioController.listarUsuarioPorId(id);

            if (usuario == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            System.out.println("Nome: " + usuario.getNome());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Email: " + usuario.getEmail());
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void atualizarUsuario() {
        System.out.println("\n=== Atualizar Usuário ===");
        int id = lerInteiro("Digite o ID do usuário a ser atualizado: ");

        try {
            Usuario usuario = usuarioController.listarUsuarioPorId(id);

            if (usuario == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            String nome = lerLinha("Digite o novo nome do usuário: ");
            String cpf = lerLinha("Digite o novo CPF do usuário: ");
            String email = lerLinha("Digite o novo email do usuário: ");

            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
                System.out.println("Preencha todos os campos!");
                return;
            }

            if (!cpf.matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")) {
                System.out.println("CPF inválido!");
                return;
            }

            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("Email inválido!");
                return;
            }

            Usuario usuarioAtualizado = new Usuario(null, nome, cpf, email);
            usuarioController.atualizarUsuario(usuarioAtualizado, id);
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deletarUsuario() {
        System.out.println("\n=== Deletar Usuário ===");
        int id = lerInteiro("Digite o ID do usuário a ser deletado: ");

        try {
            Usuario usuario = usuarioController.listarUsuarioPorId(id);

            if (usuario == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            usuarioController.deletarUsuario(id);
            System.out.println("Usuário deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void cadastrarLivro() {
        System.out.println("\n=== Cadastrar Livro ===");
        String isbn = lerLinha("Digite o ISBN do livro (format: 978-85-359-0277-2): ");
        String autor = lerLinha("Digite o nome do autor: ");
        String titulo = lerLinha("Digite o título do livro: ");
        String editora = lerLinha("Digite o nome da editora: ");
        int ano = lerInteiro("Digite o ano de lançamento do livro: ");

        if (isbn.isEmpty() || autor.isEmpty() || titulo.isEmpty() || editora.isEmpty()) {
            System.out.println("Preencha todos os campos!");
            return;
        }

        if (!isbn.matches("^\\d{3}-?\\d{2}-?\\d{3}-?\\d{4}-?\\d{1}$")) {
            System.out.println("ISBN inválido!");
            return;
        }

        Livro novoLivro = new Livro(isbn, autor, titulo, editora, ano, false);

        try {
            livroController.cadastrarLivro(novoLivro);
            System.out.println("Livro cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarLivros() {
        System.out.println("\n=== Todos os Livros ===");
        try {
            livroController.listarLivros();
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarLivroPorISBN() {
        System.out.println("\n=== Listar Livro por ISBN ===");
        String isbn = lerLinha("Digite o ISBN do livro: ");
        try {
            Livro livro = livroController.listarLivroPorISBN(isbn);

            if (livro == null) {
                System.out.println("Livro não encontrado.");
                return;
            }

            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("ISBN: " + livro.getISBN());
        } catch (SQLException e) {
            System.err.println("Erro ao listar livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void atualizarLivro() {
        System.out.println("\n=== Atualizar Livro ===");
        String isbn = lerLinha("Digite o ISBN do livro a ser atualizado: ");

        try {
            Livro livro = livroController.listarLivroPorISBN(isbn);

            if (livro == null) {
                System.out.println("Livro não encontrado.");
                return;
            }

            String autor = lerLinha("Digite o novo nome do autor: ");
            String titulo = lerLinha("Digite o novo título do livro: ");
            String editora = lerLinha("Digite o novo nome da editora: ");
            int ano = lerInteiro("Digite o novo ano de lançamento do livro: ");

            if (autor.isEmpty() || titulo.isEmpty() || editora.isEmpty()) {
                System.out.println("Preencha todos os campos!");
                return;
            }

            if (!isbn.matches("^\\d{3}-?\\d{2}-?\\d{3}-?\\d{4}-?\\d{1}$")) {
                System.out.println("ISBN inválido!");
                return;
            }

            Livro livroAtualizado = new Livro(isbn, autor, titulo, editora, ano, false);
            livroController.atualizarLivro(livroAtualizado);
            System.out.println("Livro atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao listar livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deletarLivro() {
        System.out.println("\n=== Deletar Livro ===");
        String isbn = lerLinha("Digite o ISBN do livro a ser deletado: ");

        try {
            Livro livro = livroController.listarLivroPorISBN(isbn);

            if (livro == null) {
                System.out.println("Livro não encontrado.");
                return;
            }

            livroController.deletarLivro(isbn);
            System.out.println("Livro deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao listar livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void realizarEmprestimo() {
        System.out.println("\n=== Realizar Empréstimo ===");
        int usuarioId = lerInteiro("Digite o ID do usuário: ");
        String isbn = lerLinha("Digite o ISBN do livro: ");

        try {
            Livro livro = livroController.listarLivroPorISBN(isbn);

            if (livro == null) {
                System.out.println("Livro não encontrado.");
                return;
            }
            if (livro.getEmprestado()) {
                System.out.println("Livro já está emprestado.");
                return;
            }

            Usuario usuario = usuarioController.listarUsuarioPorId(usuarioId);
            if (usuario == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            Date dataEmprestimo = new Date();
            int dias = lerInteiro("Digite o número de dias para empréstimo: ");
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataEmprestimo);
            cal.add(Calendar.DAY_OF_MONTH, dias);
            Date dataDevolucao = cal.getTime();

            Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, usuario, livro);
            emprestimoController.realizarEmprestimo(emprestimo);

            livro.setEmprestado(true);
            livroController.atualizarLivro(livro);

            System.out.println("Empréstimo realizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao listar livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarLivrosEmprestados() {
        System.out.println("\n=== Livros Emprestados ===");
        try {
            livroController.listarLivrosEmprestados();
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros emprestados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarLivrosDisponiveis() {
        System.out.println("\n=== Livros Disponíveis ===");
        try {
            livroController.listarLivrosDisponiveis();
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros disponíveis: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void devolverLivro() {
        System.out.println("\n=== Devolver Livro ===");
        String isbn = lerLinha("Digite o ISBN do livro a ser devolvido: ");

        try {
            Livro livro = livroController.listarLivroPorISBN(isbn);

            if (livro == null) {
                System.out.println("Livro não encontrado.");
                return;
            }
            if (!livro.getEmprestado()) {
                System.out.println("Livro não está emprestado.");
                return;
            }

            livro.setEmprestado(false);
            livroController.atualizarLivro(livro);
            System.out.println("Livro devolvido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao listar livro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
