
package exercicio;

public class Usuario {
    
    private String nome;
    private String login;
    private String senha;
    
    public Usuario() {
        this.nome = "";
        this.login= "";
        this.senha = "";
    }
    
    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login= login;
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
