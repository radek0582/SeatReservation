package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> getList();

    Product findById(Long id) throws ProductNotFoundException;

    void save (Product product);

    Product findRandom () throws ProductNotFoundException;

    void delete (Product product);
}
