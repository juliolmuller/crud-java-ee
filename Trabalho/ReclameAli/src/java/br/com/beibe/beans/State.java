package br.com.beibe.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public final class State implements Serializable {

    private Long id;
    private String name;
    private String abrev;

    public State() {}

    public State(Long id, String name, String abrev) {
        setId(id);
        setName(name);
        setAbrev(abrev);
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

    public String getAbrev() {
        return this.abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }
}
