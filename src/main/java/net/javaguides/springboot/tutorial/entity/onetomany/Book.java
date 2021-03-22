package net.javaguides.springboot.tutorial.entity.onetomany;

import net.javaguides.springboot.tutorial.entity.onetoone.Address;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Book {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotBlank (message = "Name is mandatory")
    @Column
    private String name;

    @ManyToOne (cascade = CascadeType.ALL)
    //@JoinColumn(name = "book_category_id")
    private BookCategory bookCategory;

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public Book(){}

    public Book(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
