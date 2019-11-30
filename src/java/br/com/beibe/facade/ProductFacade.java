package br.com.beibe.facade;

import java.sql.SQLException;
import java.util.List;
import br.com.beibe.beans.Product;
import br.com.beibe.beans.Ticket;
import br.com.beibe.dao.ProductDAO;
import br.com.beibe.dao.TicketDAO;

public abstract class ProductFacade {

    public static List<Product> listAll() {
        return ProductDAO.getList();
    }

    public static Product find(Long id) {
        return ProductDAO.find(id);
    }

    public static void save(Product product) {
        try {
            if (product.getId() == null)
                ProductDAO.insert(product);
            else
                ProductDAO.update(product);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean delete(Product product) {
        try {
            ProductDAO.delete(product.getId());
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static List<Ticket> listTickets(Product product) {
        List<Ticket> tickets = TicketDAO.getList();
        tickets.removeIf(ticket -> !ticket.getProduct().getId().equals(product.getId()));
        return tickets;
    }
}
