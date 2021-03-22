package net.javaguides.springboot.tutorial.dekorator;

public class MleczkoSojowe extends SkladnikDekorator {
    Napój napój;

    public MleczkoSojowe(Napój napój){
        this.napój = napój;
    }

//    @Override
    public String pobierzOpis(){
        return napój.pobierzOpis() + ", Mleczko sojowe";
    }

    public double koszt(){
        return napój.koszt() + 0.15;
    }
}
