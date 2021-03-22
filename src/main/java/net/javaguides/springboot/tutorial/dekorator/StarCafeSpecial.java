package net.javaguides.springboot.tutorial.dekorator;

public class StarCafeSpecial extends Napój {
    public StarCafeSpecial(){
        opis = "Kawa Star Cafe Special";
    }

    public double koszt(){
        return 0.89;
    }
}
