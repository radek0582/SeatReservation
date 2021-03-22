package net.javaguides.springboot.tutorial.dao;

import  net.javaguides.springboot.tutorial.entity.Product;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ProductDaoStaticImpl implements ProductDao{

    private static final Set<Product> PRODUCTS = new HashSet<>();

    static {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Lion1");
        p1.setDescription("Lew 1");
        p1.setTimestamp(LocalDateTime.now());
        p1.setVisitCount(0);
        p1.setImagePath("/gifs/lion1.gif");

        Product p2 = new Product();
        p2.setId(2L);
        p2.setName("Lion2");
        p2.setDescription("Lew 2");
        p2.setTimestamp(LocalDateTime.now());
        p2.setVisitCount(0);
        p2.setImagePath("/gifs/lion2.gif");

        Product p3 = new Product();
        p3.setId(3L);
        p3.setName("Zebra");
        p3.setDescription("Zebra atakuje");
        p3.setTimestamp(LocalDateTime.now());
        p3.setVisitCount(0);
        p3.setImagePath("/gifs/zebra.gif");

        PRODUCTS.add(p1);
        PRODUCTS.add(p2);
        PRODUCTS.add(p3);
    }

    @Override
    public List<Product> findAll(){
        return new ArrayList<>(PRODUCTS);
    }

    @Override
    public Product findById (Long id){
        for (Product p : PRODUCTS){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public void update (Product product){
        PRODUCTS.add(product);
    }

    public Product findRandom (){
        int maxId = PRODUCTS.size();
        Random random = new Random ();
        long randomId = random.nextInt(maxId) + 1;
        return findById(randomId);
    }

    @Override
    public void save (Product product){
        long newId = PRODUCTS.size() + 1;
        product.setId(newId);
        PRODUCTS.add(product);
    }

    @Override
    public void delete(Product product){
        PRODUCTS.remove(product);
    }
}
