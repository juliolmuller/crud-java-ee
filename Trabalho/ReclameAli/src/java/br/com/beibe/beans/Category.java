package br.com.beibe.beans;

import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("serial")
public final class Category implements Bean {

    private Long id;
    private String name;

    public Category() {}

    public Category(Long id, String name) {
        setId(id);
        setName(name);
    }

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
        List<ValError> errors = new ArrayList<>();
        return errors;
    }
}
