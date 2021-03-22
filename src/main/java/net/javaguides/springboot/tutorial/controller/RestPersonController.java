package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.dao.ProductDao;
import net.javaguides.springboot.tutorial.entity.Person;
import net.javaguides.springboot.tutorial.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestPersonController {

    @Autowired
    private ProductDao productDaoImpl;

    @GetMapping("/restPerson")
    public List<Product> getProducts(){
        return productDaoImpl.findAll();
    }


}
