package net.javaguides.springboot.tutorial.observer;

import java.util.Observable;
import java.util.Observer;

public class WyswietlBiezace implements Observer {
    Observable observable;
    float temperatura;
    float wilgotnosc;

    public WyswietlBiezace (Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    public  void update(Observable obs, Object arg){
        if (obs instanceof DanePogodowe){
            DanePogodowe danePogodowe = (DanePogodowe)obs;
            this.temperatura = danePogodowe.pobierzTemp();
            this.wilgotnosc = danePogodowe.pobCisn();
            wyswietl();
        }
    }

    public void wyswietl(){
        System.out.println("war b.: -> " + temperatura);
    }
}
