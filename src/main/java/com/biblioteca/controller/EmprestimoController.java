package com.biblioteca.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.dao.EmprestimoDao;
import com.biblioteca.model.Emprestimo;

public class EmprestimoController {
    private final EmprestimoDao emprestimoDao;
    private final Connection connection;

    public EmprestimoController(Connection connection) {
        this.connection = connection;
        this.emprestimoDao = new EmprestimoDao(connection);
    }

    public void realizarEmprestimo(Emprestimo emprestimo) throws SQLException {
        emprestimoDao.create(emprestimo);
    }

    public ArrayList<Emprestimo> listarEmprestimos() throws SQLException {
        return emprestimoDao.readAll();
    }
}
