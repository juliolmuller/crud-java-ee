package br.com.beibe.beans;

import java.util.List;

public class Category implements Bean {

    private Long id;
    private String name;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<ValError> validate() {
        return null;
    }
}
