package br.com.beibe.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import br.com.beibe.beans.Ticket;
import br.com.beibe.beans.User;
import br.com.beibe.dao.TicketDAO;

public abstract class TicketFacade {

    public static List<Ticket> listAll() {
        return TicketDAO.getList();
    }

    public static Set<Ticket> listMine(User user) {
        List<Ticket> tickets = TicketDAO.getList();
        tickets.removeIf(ticket -> !user.equals(ticket.getOpenBy()));
        return new TreeSet<>(tickets);
    }

    public static Ticket find(Long id) {
        return TicketDAO.find(id);
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
}
