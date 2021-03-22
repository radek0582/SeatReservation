package net.javaguides.springboot.tutorial.dekorator;

public class BitaSmietana extends SkladnikDekorator {
    Napój napój;

    public BitaSmietana(Napój napój){
        this.napój = napój;
    }

//    @Override
    public String pobierzOpis(){
        return napój.pobierzOpis() + ", Bita smietana";
    }

    public double koszt(){
        return napój.koszt() + 0.10;
    }
}
