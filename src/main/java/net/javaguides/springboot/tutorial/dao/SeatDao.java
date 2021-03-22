package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Comment;
import net.javaguides.springboot.tutorial.entity.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SeatDao {
    List<Seat> findAll();
    Seat findById (Long id);
    void update(Seat event);
    void save(Seat event);
    //    Product findRandom();
    void delete(Seat event);
}
