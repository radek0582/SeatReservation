package net.javaguides.springboot.tutorial.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Planet {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotBlank (message = "Name is mandatory")
    @Column
    private String name;

    public Planet (){}

    public Planet (String name){
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
