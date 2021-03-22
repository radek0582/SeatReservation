package net.javaguides.springboot.tutorial.obserwator;

import java.util.ArrayList;

public class DanePogodowe implements Podmiot{
    private ArrayList obserwatorzy;
    private float temperatura;
    private float wilgotnosc;
    private float cistnienie;

    public DanePogodowe (){
        obserwatorzy = new ArrayList();
    }

    public void zarejestrujObserwatora (Obserwator o){
        obserwatorzy.add(o);
    }

    public void usunObserwatora (Obserwator o){
        int i = obserwatorzy.indexOf(o);

        if (i >= 0){
            obserwatorzy.remove(o);
        }
    }

    public void powiadomObserwatorow (){
        for (int i = 0; i < obserwatorzy.size(); i++){
            Obserwator Obs =(Obserwator)obserwatorzy.get(i);
            Obs.aktualizacja(temperatura, wilgotnosc, cistnienie);
        }
    }

    public void odczytZmiana(){
        powiadomObserwatorow();
    }

    public void ustawOdczyty(float temperatura, float wilgotnosc, float cistnienie){
        this.temperatura = temperatura;
        this.wilgotnosc = wilgotnosc;
        this.cistnienie = cistnienie;

        odczytZmiana();
    }
}
