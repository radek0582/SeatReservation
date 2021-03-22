package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.entity.Moon;
import net.javaguides.springboot.tutorial.entity.Planet;
import org.springframework.stereotype.Service;

@Service
public class MoonValidator {

    public void validate (Moon moon){
        if (moon.getName().length() < 2)
            throw new IllegalArgumentException("Nazwa ksiezyca con. 2 znaki!");
    }
}
