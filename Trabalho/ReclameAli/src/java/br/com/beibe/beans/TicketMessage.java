package br.com.beibe.beans;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SuppressWarnings("serial")
public final class TicketMessage implements Bean, Comparable<TicketMessage> {

    private Long id;
    private String message;
    private User sender;
    private Long ticketId;
    private LocalDateTime sendDate;

    public TicketMessage() {}

    public TicketMessage(Long id, String message, User sender, Long ticket, LocalDateTime dateTime) {
        setId(id);
        setMessage(message);
        setSender(sender);
        setTicket(ticket);
        setSendDate(dateTime);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Long getTicket() {
        return this.ticketId;
    }

    public void setTicket(Long ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(LocalDateTime dateTime) {
        this.sendDate = dateTime;
    }

    @Override
    public List<ValError> validate() {
        List<ValError> errors = new ArrayList<>();
        return errors;
    }

    @Override
    public int compareTo(TicketMessage tm) {
        return this.sendDate.compareTo(tm.sendDate);
    }
}
