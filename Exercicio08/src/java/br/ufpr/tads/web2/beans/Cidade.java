package br.ufpr.tads.web2.beans;

public class Cidade {

    private Long id;
    private String nome;
    private Estado estado;

    public Cidade() {}

    public String getNome() {
        return this.nome;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
