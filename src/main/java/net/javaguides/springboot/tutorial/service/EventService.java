package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Product;

import java.util.List;

public interface EventService {

    List<Event> getList();

    Event findById(Long id) throws EventNotFoundException;

    void save(Event event);

//    Product findRandom() throws ProductNotFoundException;

    void delete(Event event);
}
