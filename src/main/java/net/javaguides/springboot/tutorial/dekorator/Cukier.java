package net.javaguides.springboot.tutorial.dekorator;

public class Cukier extends SkladnikDekorator {
    Napój napój;

    public Cukier(Napój napój){
        this.napój = napój;
    }

//    @Override
    public String pobierzOpis(){
        return napój.pobierzOpis() + ", Cukier";
    }

    public double koszt(){
        return napój.koszt() + 0.10;
    }
}
