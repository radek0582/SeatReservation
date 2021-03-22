package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.manytomany.Car;
import net.javaguides.springboot.tutorial.entity.manytomany.Driver;
import net.javaguides.springboot.tutorial.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping ("/cars")
public class CarController2 {

    @Autowired
    private CarRepository carRepository;


    @GetMapping("cars")
    public String showCars(Model model) {
        Iterable<Car> cars = carRepository.findAll();

        model.addAttribute("cars", cars);

        return "cars";
    }

    @GetMapping("car/delete/{id}")
    public String deleteCar(@PathVariable("id") long id, Model model) {
        Car car  = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        carRepository.delete(car);
        model.addAttribute("cars", carRepository.findAll());
        return "redirect:/cars/cars";
    }
}
