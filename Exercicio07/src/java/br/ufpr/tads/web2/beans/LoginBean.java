
package br.ufpr.tads.web2.beans;

public class LoginBean {

    private Long idUsuario;
    private String loginUsuario;
    private String nomeUsuario;

    public LoginBean() {}
    
    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long id) {
        this.idUsuario = id;
    }

    public String getLoginUsuario() {
        return this.loginUsuario;
    }

    public void setLoginUsuario(String login) {
        this.loginUsuario = login;
    }

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    public void setNomeUsuario(String nome) {
        this.nomeUsuario = nome;
    }
}
