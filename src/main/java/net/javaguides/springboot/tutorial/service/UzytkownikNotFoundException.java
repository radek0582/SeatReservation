package net.javaguides.springboot.tutorial.service;

public class UzytkownikNotFoundException extends Exception {
    public UzytkownikNotFoundException(){
        super("Uzytkownik not found.");
    }
}
