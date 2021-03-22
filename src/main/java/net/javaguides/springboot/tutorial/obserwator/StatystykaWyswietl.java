package net.javaguides.springboot.tutorial.obserwator;

public class StatystykaWyswietl implements Obserwator, WyswietlElement {
    private float temperatura;
    private float wilgotnosc;
    private Podmiot DanePogodowe;

    public StatystykaWyswietl(Podmiot DanePogodowe){
        this.DanePogodowe = DanePogodowe;
        DanePogodowe.zarejestrujObserwatora(this);
    }

    public void aktualizacja (float temperatura, float wilgotnosc, float cisnienie){
        this.temperatura = temperatura;
        this.wilgotnosc = wilgotnosc;
        wyswietl();
    }

    public void wyswietl(){
        System.out.println("Temp. srednia/maksymalna/minimalna: " + temperatura + " stopni C oraz " + wilgotnosc + "% wilgotnosc");
    }
}
