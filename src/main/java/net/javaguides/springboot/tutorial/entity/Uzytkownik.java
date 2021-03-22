package net.javaguides.springboot.tutorial.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;


    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

//    @NotBlank(message = "Admin field is mandatory")
    @Column(name = "admin")
    private boolean admin;

    @Column(name="active")
    private boolean active;


    public Uzytkownik(){}

    public Uzytkownik(@NotBlank(message = "Name is mandatory") String name, @NotBlank(message = "Password is mandatory") String password, boolean admin, String email) {
        this.name = name;
        this.password = password;
        this.admin = admin;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}