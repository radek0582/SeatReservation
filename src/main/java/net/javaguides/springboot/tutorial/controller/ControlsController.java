package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.State;
import net.javaguides.springboot.tutorial.entity.manytomany.Car;
import net.javaguides.springboot.tutorial.repository.CarRepository;
import net.javaguides.springboot.tutorial.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping ("/controls")
public class ControlsController {

    @Autowired
    private StateRepository stateRepository;




    @GetMapping("select")
    public String showControl(Model model) {
        Iterable<State> states = stateRepository.findAll();

        List<State> result = new ArrayList<State>();
        states.forEach(result::add);

        model.addAttribute("states", result);

        return "controls";
    }


}
