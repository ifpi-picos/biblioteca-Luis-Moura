package com.biblioteca.controller;

import com.biblioteca.dao.UsuarioDao;
import com.biblioteca.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioController {
    private final UsuarioDao usuarioDao;
    private final Connection connection;

    public UsuarioController(Connection connection) throws SQLException {
        this.connection = connection;
        this.usuarioDao = new UsuarioDao(connection);
    }

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        usuarioDao.create(usuario);
    }

    public void listarUsuarios() throws SQLException {
        ArrayList<Usuario> usuarios = usuarioDao.read();
        for (Usuario usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("------------------------------");
        }
    }

    public Usuario listarUsuarioPorId(int id) throws SQLException {
        return usuarioDao.readById(id);
    }

    public void atualizarUsuario(Usuario usuario, int id) throws SQLException {
        usuarioDao.update(usuario, id);
    }

    public void deletarUsuario(int id) throws SQLException {
        usuarioDao.delete(id);
    }
}
