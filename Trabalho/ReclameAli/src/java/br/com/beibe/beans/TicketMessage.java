package br.com.beibe.beans;

import java.util.Date;
import java.util.List;

public class TicketMessage implements Bean {

    private Long id;
    private String message;
    private User sender;
    private Date sendDate;

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

    public Date getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(Date date) {
        this.sendDate = date;
    }

    @Override
    public List<ValError> validate() {
        return null;
    }
}
