package br.com.beibe.beans;

public enum TicketStatus {
    OPEN(1, "Aberto"),
    CLOSED(2, "Fechado");

    private final int id;
    private final String name;

    private TicketStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TicketStatus ofId(int id) {
        TicketStatus[] ts =  values();
        for (int i = 0; i < ts.length; i++) {
            if (ts[i].id == id)
                return ts[i];
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
