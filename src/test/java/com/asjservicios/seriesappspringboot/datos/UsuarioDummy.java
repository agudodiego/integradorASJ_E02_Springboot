package com.asjservicios.seriesappspringboot.datos;

import com.asjservicios.seriesappspringboot.model.Usuario;

public class UsuarioDummy {

    public static Usuario getUsuarioDiego() {
        Usuario u = new Usuario();
        //u.setId_usuario(1); // No debo mandarle el id para hacer el save
        u.setUsuario("Diego");
        u.setContrasenia("Diego12345");
        u.setEmail("Diego@email.com");
        return u;
    }

    public static Usuario getUsuarioMario() {
        Usuario u = new Usuario();
        //u.setId_usuario(2); // No debo mandarle el id para hacer el save
        u.setUsuario("Mario");
        u.setContrasenia("mgomez12345");
        u.setEmail("gomez@email.com");
        return u;
    }

    public static Usuario getUsuarioDiegoConId() {
        Usuario u = new Usuario();
        u.setId_usuario(1);
        u.setUsuario("Diego");
        u.setContrasenia("Diego12345");
        u.setEmail("Diego@email.com");
        return u;
    }
}
