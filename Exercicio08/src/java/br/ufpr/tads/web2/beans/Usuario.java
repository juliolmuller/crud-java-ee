package br.ufpr.tads.web2.beans;

import br.ufpr.tads.web2.dao.UsuarioDAO;
import java.util.List;
import java.util.ArrayList;
import br.ufpr.tads.web2.utils.Validator;

public class Usuario {

    private Long id;
    private String nome;
    private String login;
    private String senha;

    public Usuario() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome =  (nome == null || nome.equals("")) ? null : nome;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login =  (login == null || login.equals("")) ? null : login.toUpperCase();
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha =  (senha == null || senha.equals("")) ? null : senha;
    }
    
    public List<Erro> validar() {
        List<Erro> erros = new ArrayList<>();
        //Usuario usuario;
        
        // Validar nome
        if (this.nome == null) {
            erros.add(new Erro("nome", "O campo 'NOME' é obrigatório."));
        } else {
            if (!Validator.isName(this.nome)) {
                erros.add(new Erro("nome", "O campo 'NOME' contém caracteres inválidos."));
            } else if (this.nome.length() < 2 || this.nome.length() > 100) {
                erros.add(new Erro("nome", "O campo 'NOME' deve ter entre 2 e 100 caracteres."));
            }
        }
        
        // Validar login
        if (this.login == null) {
            erros.add(new Erro("login", "O campo 'LOGIN' é obrigatório."));
        } else if (this.id != null) {
            if (!Validator.isLogin(this.login)) {
                erros.add(new Erro("login", "O campo 'LOGIN' contém caracteres inválidos."));
            } else if (this.login.length() < 5 || this.login.length() > 50) {
                erros.add(new Erro("login", "O campo 'LOGIN' deve ter entre 5 e 50 caracteres."));
            } else {
                Usuario usuario = UsuarioDAO.com(this.login);
                if (usuario != null && (this.id == null || !usuario.id.equals(this.id))) {
                    erros.add(new Erro("login", "O login '" + this.login + "' já está em uso por outro usuário."));
                }
            }
        }
        
        // validar senha
        if (this.senha == null) {
            erros.add(new Erro("senha", "O campo 'SENHA' é obrigatório."));
        } else {
            if (this.senha.length() < 8 || this.senha.length() > 32) {
                erros.add(new Erro("senha", "A senha deve ter entre 8 e 32 caracteres."));
            }
        }
        
        return erros;
    }
}
