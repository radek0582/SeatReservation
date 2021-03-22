package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.dao.CommentDao;
import net.javaguides.springboot.tutorial.dao.SeatDao;
import net.javaguides.springboot.tutorial.entity.Comment;
import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatDao seatDao;

    @Override
    public List<Seat> getList (){
        return seatDao.findAll();
    }

    @Override
    public List<Seat> getSeatListByEvent (Event event){
        List<Seat> allSeats = seatDao.findAll();
        List<Seat> seats = new ArrayList<>();

        for (Seat seat:allSeats){
            if (seat.getEvent() == event) {
                seats.add(seat);
            }
        }

        return seats;
    }

    @Override
    public void save(Seat seat) {
        seatDao.save(seat);
    }

    @Override
    public void delete(Seat seat){
        seatDao.delete(seat);
    }

    @Override
    public Seat findById(Long id) {
        return seatDao.findById(id);
    }
}
