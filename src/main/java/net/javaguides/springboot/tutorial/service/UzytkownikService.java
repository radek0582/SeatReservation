package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.entity.Uzytkownik;

import java.util.List;

public interface UzytkownikService {

    List<Uzytkownik> getRegisteredUsers();

    Uzytkownik findById(Long id) throws UzytkownikNotFoundException;

    void save(Uzytkownik uzytkownik);

//    Product findRandom() throws ProductNotFoundException;

    void delete(Uzytkownik uzytkownik);
}
