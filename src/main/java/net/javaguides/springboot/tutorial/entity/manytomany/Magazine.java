package net.javaguides.springboot.tutorial.entity.manytomany;

import net.javaguides.springboot.tutorial.entity.onetomany.BookCategory;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Magazine {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotBlank (message = "Name is mandatory")
    @Column
    private String name;

    @ManyToMany (cascade = CascadeType.ALL
//    ,fetch = FetchType.LAZY
    )
    //@JoinColumn(name = "book_category_id")
    private List <Author> authors;

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Magazine(){}

    public Magazine(String name){
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
