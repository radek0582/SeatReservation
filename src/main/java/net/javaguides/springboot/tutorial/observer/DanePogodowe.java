package net.javaguides.springboot.tutorial.observer;

import java.util.Observable;

public class DanePogodowe extends Observable {
    private float temperatura;
    private float cisnienie;
    private float wilgotnosc;

    public DanePogodowe (){

    }

    public void odczyty (){
        setChanged();
        notifyObservers();
    }

    public void ustawOdczyty (float temperatura, float cisnienie, float wilgotnosc){
        this.temperatura = temperatura;
        this.cisnienie = cisnienie;
        this.wilgotnosc = wilgotnosc;

        odczyty();;
    }

    public float pobierzTemp(){
        return  temperatura;
    }

    public float getWilgotnosc() {
        return wilgotnosc;
    }

    public float pobCisn(){
        return cisnienie;
    }
}
