package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.dao.EventDao;
import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{

    @Autowired//(required = false) optional bean
    private EventDao eventDaoImpl;

//    @Autowired
//    @Qualifier("jan")
//    private Person person;

    @Override
    public List<Event> getList (){
//        System.out.println(person.getName()); //TODO

        return eventDaoImpl.findAll();
    }

    public Event findById (Long id) throws EventNotFoundException{
        Event event = eventDaoImpl.findById(id);

//        if (product == null){
//            throw new ProductNotFoundException();
//        }else{
//            product.increaseVisitCount();
//            productDaoImpl.update(product);
//        }

        return getMemAndUpdate(event);
    }


    @Override
    public void save (Event event){
        eventDaoImpl.save(event);
    }

    private Event getMemAndUpdate(Event event) throws EventNotFoundException{
        if (event == null) {

            throw new EventNotFoundException();
        } else {
            event.increaseVisitCount();
            eventDaoImpl.update(event);
        }
        return event;
    }

    @Override
    public void delete(Event event){
        eventDaoImpl.delete(event);
    }
}
