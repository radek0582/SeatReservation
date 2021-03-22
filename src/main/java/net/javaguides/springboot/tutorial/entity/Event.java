package net.javaguides.springboot.tutorial.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "EVENT")
public class Event {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column(name="NAME", nullable = false)
    @NotEmpty (message = "Pole nie moze byc puste")
    @NotNull
    private String name;

    @Column(name="ARTIST")
    @NotEmpty(message = "Pole nie moze byc puste")
    @NotNull
    private String artist;

    @Column(name="COMMENT")
    @NotEmpty(message = "Pole nie moze byc puste")
    @NotNull
    private String comment;

    @Column(name="TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    @Column(name="IMAGE_PATH", unique = true, nullable = false)
    private String imagePath;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Seat> seats;

    @Column(name="VISIT_COUNT")
    private int visitCount;


//    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
//    private Category category;

    public Event(){

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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @JsonManagedReference
    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void increaseVisitCount(){
        ++this.visitCount;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + getId() + '\'' +
                "name='" + name+ '\'' +
                ", description='" + artist+ '\'' +
                '}';
    }
}
