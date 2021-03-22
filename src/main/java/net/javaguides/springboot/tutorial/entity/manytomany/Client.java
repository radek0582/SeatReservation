package net.javaguides.springboot.tutorial.entity.manytomany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="CLIENT")
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name="client_id")
    private long id;

    @NotBlank (message = "Name is mandatory")
    @Column
    private String name;


    @ManyToMany (/*mappedBy = "clients", */cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    private List <Company> companies;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @ManyToMany (/*mappedBy = "clients", */cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    private List <Continent> continents;

    public List<Continent> getContinents() {
        return continents;
    }

    public void setContinents(List<Continent> continents) {
        this.continents= continents;
    }

    public Client(){}

    public Client(String name){
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
