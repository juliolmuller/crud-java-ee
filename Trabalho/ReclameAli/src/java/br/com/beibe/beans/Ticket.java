package br.com.beibe.beans;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("serial")
public final class Ticket implements Bean {

    private Long id;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private TicketStatus status;
    private TicketType type;
    private User openBy;
    private Product product;
    private Set<TicketMessage> messages;

    public Ticket() {}

    public Ticket(Long id, LocalDateTime openingDate, LocalDateTime closingDate, TicketStatus status, TicketType type, User openBy, Product product, Set<TicketMessage> messages) {
        setId(id);
        setOpeningDate(openingDate);
        setClosingDate(closingDate);
        setStatus(status);
        setType(type);
        setOpenBy(openBy);
        setProduct(product);
        setMessages(messages);
    }

    public Ticket(Long id, LocalDateTime openingDate, LocalDateTime closingDate, TicketStatus status, TicketType type, User openBy, Product product, TicketMessage message) {
        this(id, openingDate, closingDate, status, type, openBy, product, new TreeSet<>());
        this.messages.add(message);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOpeningDate() {
        return this.openingDate;
    }

    public void setOpeningDate(LocalDateTime date) {
        this.openingDate = date;
    }

    public LocalDateTime getClosingDate() {
        return this.closingDate;
    }

    public void setClosingDate(LocalDateTime date) {
        this.closingDate = date;
    }

    public TicketStatus getStatus() {
        return this.status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public TicketType getType() {
        return this.type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public User getOpenBy() {
        return this.openBy;
    }

    public void setOpenBy(User openBy) {
        this.openBy = openBy;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<TicketMessage> getMessages() {
        return this.messages;
    }

    public void setMessages(Set<TicketMessage> messages) {
        this.messages = messages;
    }

    @Override
    public List<ValError> validate() {
        List<ValError> errors = new ArrayList<>();

        // Validar atributos obrigatórios
        if (this.type == null)
            errors.add(new ValError("type", "Selecione um tipo de atendimento"));
        if (this.product == null)
            errors.add(new ValError("product", "Selecione um produto"));
        if (this.messages == null)
            errors.add(new ValError("message", "Você deve escrever uma mensagem"));

        // VAlidar mensagem
        if (this.messages != null)
            this.messages.forEach(msg -> msg.validate().forEach(error -> errors.add(error)));
        
        return errors;
    }
}
