package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.manytomany.Author;
import net.javaguides.springboot.tutorial.entity.manytomany.Car;
import net.javaguides.springboot.tutorial.entity.manytomany.Driver;
import net.javaguides.springboot.tutorial.entity.manytomany.Magazine;
import net.javaguides.springboot.tutorial.repository.CarRepository;
import net.javaguides.springboot.tutorial.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("list2")
    public String showUpdateForm2 (Model model){
        Car mercedes = new Car("mercedes");
        Car skoda = new Car("skoda");
        Car bmw = new Car("bmw");

        Driver kubica = new Driver("kubica");
        Driver kimi = new Driver("kimi");
        Driver alonso = new Driver("alonso");

        kubica.setLastName("Robert");
        kimi.setLastName("Raikonnenn");
        alonso.setLastName("Fernando");

        List <Driver> drivers1 = new ArrayList<>();
        drivers1.add(kubica);
        drivers1.add(alonso);


        List <Driver> drivers2 = new ArrayList<>();
        drivers2.add(kimi);

        List <Driver> drivers3 = new ArrayList<>();
        drivers3.add(kubica);
        drivers3.add(kimi);
        drivers3.add(alonso);


        mercedes.setDrivers(drivers1);
        skoda.setDrivers(drivers2);
        bmw.setDrivers(drivers3);

        carRepository.save(mercedes);
        carRepository.save(skoda);
        carRepository.save(bmw);

        Iterable<Car> all = carRepository.findAll();


        return "index";
    }
}
