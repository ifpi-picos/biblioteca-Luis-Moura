package com.biblioteca.model;

public class Livro {
    private final String ISBN;
    private final String autor;
    private final String titulo;
    private final String editora;
    private final int ano;
    private Boolean emprestado;

    public Livro(String ISBN, String autor, String titulo, String editora, int ano, Boolean emprestado) {
        if (ISBN == null || autor == null || titulo == null || editora == null) {
            throw new IllegalArgumentException("Nenhum parâmetro pode ser nulo");
        }
        this.ISBN = ISBN;
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
        this.emprestado = emprestado;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public int getAno() {
        return ano;
    }

    public Boolean getEmprestado() {
        return emprestado;
    }

    public void setEmprestado(Boolean emprestado) {
        this.emprestado = emprestado;
    }
}
