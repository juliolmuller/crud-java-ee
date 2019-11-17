package br.com.beibe.dao;

import java.util.List;
import java.util.ArrayList;
import br.com.beibe.beans.Product;

public abstract class ProductDAO extends DAO {

    public static List<Product> getList() {
        return new ArrayList<>();
    }
}
