package br.com.ibridge.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


@Scope("session")
public class UsuarioBean {

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
