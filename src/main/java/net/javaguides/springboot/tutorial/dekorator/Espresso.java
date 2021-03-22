package net.javaguides.springboot.tutorial.dekorator;

public class Espresso extends Napój {
    public Espresso(){
        opis = "Kawa Espresso";
    }

    public double koszt(){
        return 1.99;
    }
}
