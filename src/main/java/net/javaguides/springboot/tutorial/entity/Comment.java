package net.javaguides.springboot.tutorial.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "COMMENT")
@Entity
public class Comment{//} extends AbstractPersistable<Long>{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column (name="NICKNAME", nullable = false)
    @NotNull
    @NotEmpty
    private String nickname;

    @Column (name="TEXT", nullable = false)
    @NotNull
    @NotEmpty
    private String text;

    @Column(name="TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name="PRODUCT_ID", referencedColumnName = "ID")
    private Product product;

    public Comment (){
    }

    @PrePersist
    public void onPersist(){
        this.setTimestamp(LocalDateTime.now());
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @JsonBackReference
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
