package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentValidator {

    public void validate(Student student){
        if (student.getName().length() < 2)
            throw new IllegalArgumentException("dlugosc imienia con. 2 znaki");
    }
}
