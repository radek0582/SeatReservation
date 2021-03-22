package net.javaguides.springboot.tutorial.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "SEAT")
@Entity
public class Seat {//} extends AbstractPersistable<Long>{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column (name="NUMBER", nullable = false)
    private int number;

    @Column (name="STATUS", nullable = false)
    @NotNull
    @NotEmpty
    private String status;

    @Column (name="COMMENT", nullable = false)
    @NotNull
    @NotEmpty
    private String comment;

    @Column (name="POSX", nullable = false)
    private int posX;

    @Column (name="POSY", nullable = false)
    private int posY;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name="EVENT_ID", referencedColumnName = "ID")
    private Event event;

    public Seat(){
    }

    //@Override
    public Long getId(){
        return id;
        //return super.getId();
    }

    //@Override
    public void setId(Long id){
        this.id = id;
      //  super.setId(id);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonBackReference
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
