package br.com.beibe.config;

public enum AccessRole {

    CLIENTE("cliente"),
    FUNCIONARIO("funcionario"),
    GERENTE("gerente");

    private final String role;

    AccessRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
