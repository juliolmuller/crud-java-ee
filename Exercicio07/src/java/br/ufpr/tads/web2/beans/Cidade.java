package br.ufpr.tads.web2.beans;

public class Cidade {

    private int id;
    private String nome;
    private Estado estado;

    public Cidade() {}

    public String getNome() {
        return this.nome;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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
