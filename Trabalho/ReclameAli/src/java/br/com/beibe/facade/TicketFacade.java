package br.com.beibe.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import br.com.beibe.beans.Ticket;
import br.com.beibe.beans.TicketMessage;
import br.com.beibe.beans.TicketStatus;
import br.com.beibe.beans.TicketType;
import br.com.beibe.beans.User;
import br.com.beibe.dao.TicketDAO;
import br.com.beibe.dao.TicketMessageDAO;
import br.com.beibe.dao.TicketTypeDAO;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class TicketFacade {

    public static Set<Ticket> listAll() {
        List<Ticket> tickets = TicketDAO.getList();
        return new TreeSet<>(tickets);
    }

    public static Set<Ticket> listOpen() {
        List<Ticket> tickets = TicketDAO.getList();
        tickets.removeIf(ticket -> ticket.getStatus().equals(TicketStatus.CLOSED));
        return new TreeSet<>(tickets);
    }

    public static Set<Ticket> listOpenFrom(long days) {
        LocalDateTime now = LocalDateTime.now();
        List<Ticket> tickets = TicketDAO.getList();
        tickets.removeIf(ticket -> ticket.getStatus().equals(TicketStatus.CLOSED) || ticket.getOpeningDate().until(now, ChronoUnit.DAYS) < days);
        return new TreeSet<>(tickets);
    }

    public static Set<Ticket> listMine(User user) {
        List<Ticket> tickets = TicketDAO.getList();
        tickets.removeIf(ticket -> !user.equals(ticket.getOpenBy()));
        return new TreeSet<>(tickets);
    }

    public static Set<Ticket> listMyOpen(User user) {
        List<Ticket> tickets = TicketDAO.getList();
        tickets.removeIf(ticket -> !user.equals(ticket.getOpenBy()) || ticket.getStatus().equals(TicketStatus.CLOSED));
        return new TreeSet<>(tickets);
    }

    public static Ticket find(Long id) {
        return TicketDAO.find(id);
    }
    
    public static TicketType findType(long id) {
        return TicketTypeDAO.find(id);
    }
    
    public static TicketStatus findStatus(int id) {
        return TicketStatus.ofId(id);
    }

    public static void save(Ticket ticket) {
        try {
            if (ticket.getId() == null)
                TicketDAO.insert(ticket);
            else
                TicketDAO.update(ticket);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static void save(TicketMessage message) {
        try {
            TicketMessageDAO.insert(message);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
