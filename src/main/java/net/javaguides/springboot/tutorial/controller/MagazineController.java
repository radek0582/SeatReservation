package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Planet;
import net.javaguides.springboot.tutorial.entity.manytomany.Author;
import net.javaguides.springboot.tutorial.entity.manytomany.Magazine;
import net.javaguides.springboot.tutorial.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class MagazineController {

    @Autowired
    private MagazineRepository magazineRepository;

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        Magazine magazine1 = new Magazine("Gazeta 1");
        Author author1 = new Author("John K.");
        author1.setLastName("tets");
        Author author2 = new Author("Philip D.");
        author2.setLastName("tets333");

        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);
//        List<Author> list = Arrays.asList(author1, author2);

        magazine1.setAuthors(authors);
        magazineRepository.save(magazine1);

        Magazine magazine2 = new Magazine("Newsweek");
        magazine2.setAuthors(authors);

        magazineRepository.save(magazine2);

        magazineRepository.delete(magazine1);

        Iterable<Magazine> all = magazineRepository.findAll();

        return "index";
    }
}
