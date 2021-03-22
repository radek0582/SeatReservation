package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.service.EventService;
import net.javaguides.springboot.tutorial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/events/")
@Controller
public class EventController {

    @Autowired
    private EventService eventServiceImpl;

    @GetMapping
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("event/list");
        mav.addObject("list", eventServiceImpl.getList());

        return mav;
    }

    @GetMapping ("listby/{sortingMethod}")
    public ModelAndView listByName(@PathVariable("sortingMethod") String sortingMethod){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("event/list");

        List<Event> events = eventServiceImpl.getList();
        List<Event> sortedEvents = new ArrayList<>();

        if (sortingMethod.equals("name")) {
            sortedEvents = events.stream()
//                    .sorted(Comparator.comparing(Product::getName))
//                    .sorted(Comparator.comparing(product -> product.getName().toLowerCase()))
                    .sorted(Comparator.comparing(event -> event.getName().toLowerCase()))
                    .collect(Collectors.toCollection(ArrayList::new));

//            ArrayList<String> names = products.stream()
//                    .map(product -> product.getName())
//                    .map(name -> name.toLowerCase())
//                    .sorted(Comparator.comparing(lowerCaseName -> lowerCaseName))
//                    .collect(Collectors.toCollection(ArrayList::new));


        }

        if (sortingMethod.equals("mostpopular")) {
            sortedEvents = events.stream()
                    .sorted(Comparator.comparing(Event::getVisitCount)
                    .reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        mav.addObject("list", sortedEvents);

        return mav;
    }

    private static boolean isNot(Event event, String name){
//        System.out.println("i'm here");
        System.out.println("opis wyd: " + event.getArtist() + "nazwa wyd: " + event.getName());

        return !event.getName().equals(name);
    }
}
