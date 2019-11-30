package br.com.beibe.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public final class TicketType implements Serializable {

    private Long id;
    private String name;

    public TicketType() {}

    public TicketType(Long id, String name) {
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
}
