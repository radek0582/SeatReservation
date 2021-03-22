package net.javaguides.springboot.tutorial.entity.manytomany;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Car {

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
    public List <Driver> drivers;

    public List<Driver> getAuthors() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public Car(){}

    public Car(String name){
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
