package net.javaguides.springboot.tutorial.obserwator;

public class PrognozaWyswietl implements Obserwator, WyswietlElement {
    private float temperatura;
    private float wilgotnosc;
    private Podmiot DanePogodowe;

    public PrognozaWyswietl(Podmiot DanePogodowe){
        this.DanePogodowe = DanePogodowe;
        DanePogodowe.zarejestrujObserwatora(this);
    }

    public void aktualizacja (float temperatura, float wilgotnosc, float cisnienie){
        this.temperatura = temperatura;
        this.wilgotnosc = wilgotnosc;
        wyswietl();
    }

    public void wyswietl(){
        System.out.println("Prognoza: " + "");
    }
}
