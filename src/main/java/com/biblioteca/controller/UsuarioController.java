package com.biblioteca.controller;

import java.util.ArrayList;

import com.biblioteca.dao.UsuarioDao;
import com.biblioteca.model.Usuario;

public class UsuarioController {
  private final UsuarioDao usuarioDao;

  public UsuarioController() {
    this.usuarioDao = new UsuarioDao();
  }

  public void cadastrarUsuario(Usuario usuario) {
    usuarioDao.create(usuario);
  }

  public void listarUsuarios() {
    ArrayList<Usuario> usuarios = usuarioDao.read();
      for (Usuario usuario : usuarios) {
          System.out.println("Nome: " + usuario.getNome());
          System.out.println("CPF: " + usuario.getCpf());
          System.out.println("Email: " + usuario.getEmail());
          System.out.println();
      }
  }

  public void atualizarUsuario(Usuario usuario, int id) {
    usuarioDao.update(usuario, id);
  }

  public void deletarUsuario(int id) {
    usuarioDao.delete(id);
  }
}
