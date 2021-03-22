package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class EventDaoImpl implements EventDao {

    @Autowired
    private EventDaoCrud eventDaoCrud;

    @Override
    public List<Event> findAll() {
        Iterable<Event> coll = eventDaoCrud.findAllOrderByTimestampDesc();
        List<Event> events = new ArrayList<>();

//        List<Product> sortedList = products.stream()
//                .sorted(Comparator.comparingInt(Product::getTimestamp))
//                .collect(Collectors.toList());

        coll.forEach(events::add);    // java 8
        return events;
    }

    private Sort sortById (){
        return new Sort(Sort.Direction.ASC, "timestamp");
    }

    @Override
    public Event findById(Long id) {
        return eventDaoCrud.findById(id).get();
    }

    @Override
    public void update(Event event) {
        this.save(event);
    }

    @Override
    public void save(Event event) {
        eventDaoCrud.save(event);
    }

    @Override
    public void delete(Event event){
        eventDaoCrud.delete(event);
    }
}
