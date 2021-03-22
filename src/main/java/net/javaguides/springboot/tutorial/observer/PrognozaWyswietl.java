package net.javaguides.springboot.tutorial.observer;

import net.javaguides.springboot.tutorial.obserwator.WyswietlElement;

import java.util.Observable;
import java.util.Observer;

public class PrognozaWyswietl implements Observer, WyswietlElement{
    private float biezaceCisn = 1010.f;
    private float ostatnieCisn;

    public PrognozaWyswietl(Observable observable){
        observable.addObserver(this);
    }


    public void update(Observable observable, Object arg){
        if (observable instanceof DanePogodowe){
            DanePogodowe danePogodowe = (DanePogodowe) observable;
            ostatnieCisn = biezaceCisn;
            biezaceCisn = danePogodowe.pobCisn();
            wyswietl();
        }
    }

    public void wyswietl (){
        System.out.println("Prognoza: " + biezaceCisn);
    }
}
