package br.com.beibe.beans;

import java.io.Serializable;

public class State implements Serializable {

    private Long id;
    private String name;
    private String abrev;

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

    public String getAbrev() {
        return this.abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }
}
