package net.javaguides.springboot.tutorial.utils;

import net.javaguides.springboot.tutorial.entity.State;
import net.javaguides.springboot.tutorial.entity.Uzytkownik;
import net.javaguides.springboot.tutorial.entity.manytomany.Client;
import net.javaguides.springboot.tutorial.entity.manytomany.Company;
import net.javaguides.springboot.tutorial.entity.manytomany.Continent;
import net.javaguides.springboot.tutorial.repository.CompanyRepository;
import net.javaguides.springboot.tutorial.repository.ContinentRepository;
import net.javaguides.springboot.tutorial.repository.StateRepository;
import net.javaguides.springboot.tutorial.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
//@Order(1)
public class UzytkownikInitializer implements CommandLineRunner {
    @Autowired
    UzytkownikRepository uzytkownikRepository;

    @Value("${myapplication.env}")
    String env;

    @Override
    public void run(String... args) throws Exception {

        if (env.equals("dev")) {
            System.out.println("inicjalizacja uzytk.");

            Uzytkownik jan = new Uzytkownik("r1test", "r", true, "radsfldek@gmail.com");
            uzytkownikRepository.save(jan);;

            Uzytkownik jan2 = new Uzytkownik("r2test", "r", true, "r@gmail.com");
            uzytkownikRepository.save(jan2);;

            Uzytkownik jan3 = new Uzytkownik("r3test", "r", true, "rsol@gmail.com");
            uzytkownikRepository.save(jan3);;
        }
    }
}
