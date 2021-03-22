package net.javaguides.springboot.tutorial.entity.manytomany;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="CONTINENT")
public class Continent {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotBlank (message = "Name is mandatory")
    @Column
    private String name;

    @ManyToMany (mappedBy = "continents", cascade = {CascadeType.PERSIST}
//    ,fetch = FetchType.LAZY
    )
//    @JoinColumn(name = "continent_id")
    private List <Client> clients;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Continent(){}

    public Continent(String name){
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
