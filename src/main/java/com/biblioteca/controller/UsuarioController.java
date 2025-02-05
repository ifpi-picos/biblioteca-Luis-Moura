package com.biblioteca.controller;

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
}
