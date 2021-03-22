package net.javaguides.springboot.tutorial.dekorator;

public class Bezkofeinowa extends Napój {
    public Bezkofeinowa(){
        opis = "Kawa bezkofeinowa";
    }

    public double koszt(){
        return 1.05;
    }
}
