package br.com.beibe.config;

public enum AccessRole {
    CLIENTE(10, "cliente"),
    FUNCIONARIO(20, "funcionario"),
    GERENTE(30, "gerente");

    private int id;
    private String name;

    private AccessRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static AccessRole of(int id) {
        for (AccessRole a : values()) {
            if (id == a.id)
                return a;
        }
        return null;
    }

    public static AccessRole of(String name) {
        for (AccessRole a : values()) {
            if (name.equals(a.name))
                return a;
        }
        return null;
    }
}
