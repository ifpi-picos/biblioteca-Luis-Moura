package com.biblioteca.controller;

import java.util.ArrayList;

import com.biblioteca.dao.EmprestimoDao;
import com.biblioteca.model.Emprestimo;

public class EmprestimoController {
    private final EmprestimoDao emprestimoDao;

    public EmprestimoController() {
        this.emprestimoDao = new EmprestimoDao();
    }

    public void realizarEmprestimo(Emprestimo emprestimo) {
        emprestimoDao.create(emprestimo);
    }

    public ArrayList<Emprestimo> listarEmprestimos() {
        return emprestimoDao.readAll();
    }
}
