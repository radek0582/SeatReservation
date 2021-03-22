package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Comment;
import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.service.EventNotFoundException;
import net.javaguides.springboot.tutorial.service.EventService;
import net.javaguides.springboot.tutorial.service.ProductNotFoundException;
import net.javaguides.springboot.tutorial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/event")
@Controller
public class EventDetailsController {

    @Autowired
    private EventService eventServiceImpl;

    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable("id") Long id){

        if (id == null || id <0)
            return redirect();
        else {
            try {
                Event event = eventServiceImpl.findById(id);
                ModelAndView mav = new ModelAndView("event/details");
                mav.addObject("event", event);
//                mav.addObject("comment", new Comment());
                return mav;
            } catch (EventNotFoundException e) {
                return redirect();
            }
        }
    }

    @GetMapping("/delete/{idEvent}")
    public ModelAndView deleteEvent(@PathVariable("idEvent") long id) {
        Event event = null;

        try {
            event = eventServiceImpl.findById(id);
        } catch (EventNotFoundException e) {
            e.printStackTrace();
        }

//                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        eventServiceImpl.delete(event);
//        model.addAttribute("clients", commentServiceImpl.findAll());
//        return "redirect:/product/details";
        return new ModelAndView("redirect:/");
    }

    private ModelAndView redirect (){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }
}
