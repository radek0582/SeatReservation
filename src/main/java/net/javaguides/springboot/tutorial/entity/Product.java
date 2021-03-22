package net.javaguides.springboot.tutorial.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class Product{//} extends AbstractPersistable<Long>{

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column(name="NAME", nullable = false)
    @NotEmpty (message = "Pole nie moze byc puste")
    @NotNull
    private String name;

    @Column(name="DESCRIPTION")
    @NotEmpty(message = "Pole nie moze byc puste")
    @NotNull
    private String description;

    @Column(name="TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    @Column(name="IMAGE_PATH", unique = true, nullable = false)
    private String imagePath;

    @Column(name="VISIT_COUNT")
    private int visitCount;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments;

//    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
//    private Category category;

    public Product(){

    }

    @PrePersist
    public void onPersist () {
        this.setTimestamp(LocalDateTime.now());
    }

    public void setId (Long id){
        this.id = id;
        //super.setId(id);
    }

    public Long getId(){
        return id;
        //return super.getId();
    }

//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public void increaseVisitCount(){
        ++this.visitCount;
    }

    @JsonManagedReference
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", imagePath='" + imagePath + '\'' +
                ", visitCount=" + visitCount +
                '}';
    }
}
