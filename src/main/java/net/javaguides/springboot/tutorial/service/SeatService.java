package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.entity.Comment;
import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatService {
    List<Seat> getList();
    void save(Seat seat);
    void delete(Seat seat);
    List<Seat> getSeatListByEvent(Event event);

    Seat findById(Long id);
    //Comment findById (Long id);
}
