package net.javaguides.springboot.tutorial.service;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(){
        super("Product not found.");
    }
}
