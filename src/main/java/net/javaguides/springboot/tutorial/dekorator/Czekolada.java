package net.javaguides.springboot.tutorial.dekorator;

public class Czekolada extends SkladnikDekorator {
    Napój napój;

    public Czekolada (Napój napój){
        this.napój = napój;
    }

//    @Override
    public String pobierzOpis(){
        return napój.pobierzOpis() + ", Czekolada";
    }

    public double koszt(){
        return napój.koszt() + 0.20;
    }
}
