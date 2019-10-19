package br.com.beibe.beans;

import java.util.Date;
import java.io.Serializable;

public class TicketMessage implements Serializable {

    private long id;
    private String message;
    private User sender;
    private Date sendDate;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
}
