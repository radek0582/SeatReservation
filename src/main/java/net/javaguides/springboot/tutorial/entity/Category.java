//package net.javaguides.springboot.tutorial.entity;
//
//import net.javaguides.springboot.tutorial.entity.manytomany.Client;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import java.util.List;
//
//@Entity
//@Table(name="CATEGORY")
//public class Category {
//
//    @Id
//    @GeneratedValue (strategy = GenerationType.AUTO)
//    private long id;
//
//    @NotBlank (message = "Name is mandatory")
//    @Column
//    private String name;
//
//    @OneToMany (mappedBy = "category", cascade = {CascadeType.MERGE})
//    private List <Product> products;
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products= products;
//    }
//
//    public Category(){}
//
//    public Category(String name){
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
