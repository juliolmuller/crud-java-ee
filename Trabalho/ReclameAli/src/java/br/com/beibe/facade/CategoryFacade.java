package br.com.beibe.facade;

import java.sql.SQLException;
import java.util.List;
import br.com.beibe.beans.Category;
import br.com.beibe.beans.Product;
import br.com.beibe.dao.CategoryDAO;
import br.com.beibe.dao.ProductDAO;

public abstract class CategoryFacade {

    public static List<Category> listAll() {
        return CategoryDAO.getList();
    }

    public static Category find(Long id) {
        return CategoryDAO.find(id);
    }

    public static void save(Category category) {
        try {
            if (category.getId() == null)
                CategoryDAO.insert(category);
            else
                CategoryDAO.update(category);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean delete(Category category) {
        try {
            CategoryDAO.delete(category.getId());
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static List<Product> listProducts(Category category) {
        return ProductDAO.getList();
    }
}
