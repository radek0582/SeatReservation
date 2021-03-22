package net.javaguides.springboot.tutorial.utils;

import net.javaguides.springboot.tutorial.dao.ProductDaoCrud;
//import net.javaguides.springboot.tutorial.entity.Category;
import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.entity.State;
import net.javaguides.springboot.tutorial.entity.manytomany.Client;
import net.javaguides.springboot.tutorial.entity.manytomany.Company;
import net.javaguides.springboot.tutorial.entity.manytomany.Continent;
import net.javaguides.springboot.tutorial.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
//@Order(1)
public class DataInitializer implements CommandLineRunner {
    @Autowired
    StateRepository stateRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ContinentRepository continentRepository;

    @Autowired
    ClientRepository clientRepository;

//    @Autowired
//    CategoryRepository categoryRepository;

    @Autowired
    ProductDaoCrud productDaoCrud;

    @Value("${myapplication.env}")
    String env;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("test 1");
        System.out.println(env);




        if (env.equals("dev") || env.equals("prodfirst")) {
//            productDaoCrud.addColumn();

            Company apple = new Company("Apple");
            Company microsoft = new Company("Microsoft");
            Company google = new Company("Google");
            Company volvo = new Company("Volvo");
            Company lidl = new Company("Lidl");


            companyRepository.save(apple);
            companyRepository.save(microsoft);
            companyRepository.save(google);
            companyRepository.save(volvo);
            companyRepository.save(lidl);

            Continent europe = new Continent("Europe");
            Continent northAmerica = new Continent("North America");
            Continent asia = new Continent("Asia");
            Continent africa = new Continent("Africa");

            continentRepository.save(europe);
            continentRepository.save(northAmerica);
            continentRepository.save(asia);
            continentRepository.save(africa);

//            Category fun = new Category();
//            fun.setName("fun");
//            categoryRepository.save(fun);



            Product mem = new Product();
//            mem.setTimestamp(LocalDateTime.now());
//            mem.setImagePath("1592352022-30377634.jpg");
            mem.setName("mem1");
            mem.setImagePath("1592303973-unicef-one-shot-nerd.jpg");
            mem.setTimestamp(LocalDateTime.now());
            mem.setVisitCount(0);
            mem.setDescription("234234");
//            mem.setCategory(fun);
            productDaoCrud.save(mem);
        }

    }
}
