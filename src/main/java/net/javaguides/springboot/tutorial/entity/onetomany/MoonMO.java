package net.javaguides.springboot.tutorial.entity.onetomany;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class MoonMO {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotBlank (message = "Name is mandatory")
    @Column
    private String name;

    @ManyToOne (cascade = {CascadeType.ALL})
    //@JoinColumn(name = "book_category_id")
    private PlanetOM planetOM;

    @ManyToOne (cascade = CascadeType.ALL)
    private Galaxy galaxy;

    public PlanetOM getPlanetOM() {
        return planetOM;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public void setPlanetOM(PlanetOM planetOM) {
        this.planetOM = planetOM;
    }

    public MoonMO(){}

    public MoonMO(String name){
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
