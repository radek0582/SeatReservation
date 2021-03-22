package net.javaguides.springboot.tutorial.entity.onetomany;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Galaxy {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotBlank (message = "Name is mandatory")
    @Column
    private String name;

    @OneToMany (mappedBy = "galaxy"
//    ,fetch = FetchType.LAZY // dociaganie zaleznosci leniwe// druga opcja EAGER
    )
    private Set<MoonMO> moons;

    public Set<MoonMO> getMoons() {
        return moons;
    }

    public void setMoons(Set<MoonMO> moons) {
        this.moons = moons;
    }

    public Galaxy(){}

    public Galaxy(String name){
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
