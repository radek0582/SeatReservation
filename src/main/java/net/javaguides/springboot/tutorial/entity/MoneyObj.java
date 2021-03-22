package net.javaguides.springboot.tutorial.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class MoneyObj {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private int amount;

    public MoneyObj (){

    }

    public MoneyObj (@NotBlank(message = "amount is mandatory") int amount){
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
