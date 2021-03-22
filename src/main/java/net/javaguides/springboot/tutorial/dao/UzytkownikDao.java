package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Uzytkownik;

import java.util.List;

public interface UzytkownikDao {
    List<Uzytkownik> findAll();
    Uzytkownik findById(Long id);
    void update(Uzytkownik uzytkownik);
    void save(Uzytkownik uzytkownik);
//    Product findRandom();
    void delete(Uzytkownik uzytkownik);

}
