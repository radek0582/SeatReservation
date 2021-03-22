package net.javaguides.springboot.tutorial.restApi;

import net.javaguides.springboot.tutorial.entity.Moon;
import net.javaguides.springboot.tutorial.entity.Planet;
import net.javaguides.springboot.tutorial.entity.Student;
import net.javaguides.springboot.tutorial.entity.onetomany.*;
import net.javaguides.springboot.tutorial.entity.onetoone.Address;
import net.javaguides.springboot.tutorial.entity.onetoone.User;
import net.javaguides.springboot.tutorial.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {
    private final PlanetRepository planetRepository;
    private final MoonRepository moonRepository;
    private final UserRepository userRepository;
    private final MoonMORepository moonMORepository;
    //private final BookRepository bookRepository;
    //private final BookCategoryRepository bookCategoryRepository;
    private final GalaxyRepository galaxyRepository;

    //@Autowired
    private BookRepository bookRepository;

    @Autowired
    public MyRestController(GalaxyRepository galaxyRepository, PlanetRepository planetRepository, MoonRepository moonRepository, MoonMORepository moonMORepository, UserRepository userRepository, BookRepository bookRepository) {
        this.planetRepository = planetRepository;
        this.moonRepository = moonRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.moonMORepository = moonMORepository;
        //this.bookCategoryRepository = bookCategoryRepository;
        this.galaxyRepository = galaxyRepository;
    }

    @GetMapping("apisolarsystem")
    public Iterable<Planet> getSolarSystem() {

        Iterable<Planet> planets = planetRepository.findAll();
        //Iterable<Moon> moons = moonRepository.findAll();

        return planets;
    }

    @GetMapping("apisolarsystem2")
    public List getSolarSystem2() {

        //List solarsystem = planetRepository.findAll().iterator();

        List allplanets = new ArrayList<>();
        planetRepository.findAll().iterator().forEachRemaining(allplanets::add);

        List allmoons = new ArrayList<>();
        moonRepository.findAll().iterator().forEachRemaining(allmoons::add);

        //Planet planet = planetRepository.findAll().iterator().next();

        //System.out.println(planet);

        List solarsystem = new ArrayList<String>(allplanets);
        solarsystem.addAll(allmoons);

        return solarsystem;
    }

    @GetMapping("rr")
    public String getAllStudents2() {
        return "test";
    }
    @GetMapping("onetoone")
    public String onetoone() {


        User jan = new User("Jan");
        jan.setAddress(new Address ("Lublin"));

        userRepository.save(jan);

        return "test";
    }
    @GetMapping("onetomany")
    public String onetomany() {

        BookCategory bookClassic = new BookCategory("klasyka");
        BookCategory bookBiography = new BookCategory("biografie");

        Book book1 = new Book("Pan Tadeusz");

        //BookCategory bookCategory = new BookCategory("klasyka");
        //bookCategoryRepository.save(bookCategory);
        book1.setBookCategory(bookClassic);

        //book1.setBookCategory(bookCategory);
        Book book2 = new Book ("Ogniem i mieczem");
        book2.setBookCategory(bookClassic);

        bookRepository.save(book1);
        bookRepository.save(book2);



        return "test";
    }

    @GetMapping("onetomanymoons")
    public String onetomanymoons() {

        PlanetOM planetOMEarth = new PlanetOM("earth");
        PlanetOM planetOMMars = new PlanetOM("mars");
        PlanetOM planetOMJupiter = new PlanetOM("jupiter");
        PlanetOM planetOMSaturn = new PlanetOM("saturn");

        MoonMO moon1 = new MoonMO("Ksieyzc");

        //BookCategory bookCategory = new BookCategory("klasyka");
        //bookCategoryRepository.save(bookCategory);
        moon1.setPlanetOM(planetOMEarth);

        //book1.setBookCategory(bookCategory);
        MoonMO moon2 = new MoonMO ("Phobos");
        moon2.setPlanetOM(planetOMMars);

        MoonMO moon3 = new MoonMO ("Deimos");
        moon3.setPlanetOM(planetOMMars);

        MoonMO moon4 = new MoonMO ("Titan");
        moon4.setPlanetOM(planetOMSaturn);

        moonMORepository.save(moon1);
        moonMORepository.save(moon2);
        moonMORepository.save(moon3);
        moonMORepository.save(moon4);

        return "test";
    }

    @GetMapping("galaxycreator")
    public String galaxycreator() {


        MoonMO moon1 = new MoonMO("Ksieyzc");
        MoonMO moon4 = new MoonMO ("Titan");
        MoonMO moon101 = new MoonMO ("Unknown 1");

        Galaxy galaxy1 = new Galaxy("Andromeda");
        Galaxy galaxy2 = new Galaxy("Milky Way");

        PlanetOM planet1 = new PlanetOM ("Mars");

        moon4.setPlanetOM(planet1);

        moon1.setGalaxy(galaxy1);
        moon4.setGalaxy(galaxy1);
        moon101.setGalaxy(galaxy2);

        galaxyRepository.save(galaxy1);
        galaxyRepository.save(galaxy2);

        moonMORepository.save(moon1);
        moonMORepository.save(moon4);
        moonMORepository.save(moon101);


        return "test";
    }
}
