package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SeatDaoImpl implements SeatDao {

    @Autowired
    private SeatDaoCrud seatDaoCrud;

    @Override
    public List<Seat> findAll() {
        Iterable<Seat> coll = seatDaoCrud.findAll();
        List<Seat> seats = new ArrayList<>();

//        List<Product> sortedList = products.stream()
//                .sorted(Comparator.comparingInt(Product::getTimestamp))
//                .collect(Collectors.toList());

        coll.forEach(seats::add);    // java 8
        return seats;
    }

    @Override
    public void update(Seat seat) {
        this.save(seat);
    }

    @Override
    public void delete(Seat seat){
        seatDaoCrud.delete(seat);
    }

    @Override
    public void save(Seat seat) {
        seatDaoCrud.save(seat);
    }

    @Override
    public Seat findById(Long id) {
        return seatDaoCrud.findById(id).get();
    }
}
