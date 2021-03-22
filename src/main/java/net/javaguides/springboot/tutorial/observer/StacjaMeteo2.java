package net.javaguides.springboot.tutorial.observer;



public class StacjaMeteo2 {

    public static void main (String [] args){
        DanePogodowe danePogodowe = new DanePogodowe();

        WyswietlBiezace wyswietlBiezace = new WyswietlBiezace(danePogodowe);
//        StatystykaWyswietl statystykaWyswietl = new StatystykaWyswietl(danePogodowe);
        PrognozaWyswietl prognozaWyswietl = new PrognozaWyswietl(danePogodowe);

        danePogodowe.ustawOdczyty(26.6f, 65, 1013.1f);


//        danePogodowe.odczytZmiana();
    }

}
