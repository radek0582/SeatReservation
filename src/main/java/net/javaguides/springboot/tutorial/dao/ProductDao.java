package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductDao {
    List<Product> findAll ();
    Product findById (Long id);
    void update (Product product);
    void save (Product product);
    Product findRandom();
    void delete (Product product);

}
