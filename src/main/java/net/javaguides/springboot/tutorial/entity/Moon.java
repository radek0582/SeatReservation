package net.javaguides.springboot.tutorial.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Moon {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotBlank (message = "Name is mandatory")
    private String name;

    public Moon (){}

    public Moon (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
