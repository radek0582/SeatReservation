package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Product;

import java.util.List;

public interface EventDao {
    List<Event> findAll();
    Event findById(Long id);
    void update(Event event);
    void save(Event event);
//    Product findRandom();
    void delete(Event event);

}
