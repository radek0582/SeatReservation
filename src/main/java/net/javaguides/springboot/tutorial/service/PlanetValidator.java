package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.entity.Planet;
import org.springframework.stereotype.Service;

@Service
public class PlanetValidator {

    public void validate (Planet planet){
        if (planet.getName().length() < 2)
            throw new IllegalArgumentException("Nazwa planety con. 2 znaki!");
    }
}
