package br.com.beibe.facade;

import java.util.List;
import br.com.beibe.beans.Category;
import br.com.beibe.beans.ValError;
import br.com.beibe.dao.CategoryDAO;

public abstract class CategoryFacade {

    public static List<Category> listAll() {
        return CategoryDAO.getList();
    }

    public static List<ValError> validate(Category category) {
        return category.validate();
    }
}
