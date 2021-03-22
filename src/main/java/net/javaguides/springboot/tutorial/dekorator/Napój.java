package net.javaguides.springboot.tutorial.dekorator;

public abstract class Napój {
    String opis = "Napój nieznany";

    public String pobierzOpis() {
        return opis;
    }

    public abstract double koszt();
}
