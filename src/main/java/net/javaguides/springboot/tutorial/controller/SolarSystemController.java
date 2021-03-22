package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Moon;
import net.javaguides.springboot.tutorial.entity.Planet;
import net.javaguides.springboot.tutorial.entity.Student;
import net.javaguides.springboot.tutorial.entity.onetomany.Galaxy;
import net.javaguides.springboot.tutorial.entity.onetomany.MoonMO;
import net.javaguides.springboot.tutorial.repository.GalaxyRepository;
import net.javaguides.springboot.tutorial.repository.MoonMORepository;
import net.javaguides.springboot.tutorial.repository.MoonRepository;
import net.javaguides.springboot.tutorial.repository.PlanetRepository;
import net.javaguides.springboot.tutorial.service.MoonValidator;
import net.javaguides.springboot.tutorial.service.PlanetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping ("/solarsystem/")
public class SolarSystemController {

    private final PlanetRepository planetRepository;
    private final MoonRepository moonRepository;
    private final MoonMORepository moonMORepository;
    private final GalaxyRepository galaxyRepository;

    private PlanetValidator planetValidator;
    private MoonValidator moonValidator;

    public SolarSystemController(PlanetRepository planetRepository, PlanetValidator planetValidator, MoonRepository moonRepository, MoonValidator moonValidator,
        MoonMORepository moonMORepository, GalaxyRepository galaxyRepository) {
        this.planetRepository = planetRepository;
        this.planetValidator = planetValidator;
        this.moonRepository = moonRepository;
        this.moonValidator = moonValidator;
        this.moonMORepository = moonMORepository;
        this.galaxyRepository = galaxyRepository;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        Iterable<Planet> planets = planetRepository.findAll();
        model.addAttribute("planets", planets);
        return "index";
    }

    @GetMapping("planets")
    public String showPlanets(Model model) {
        Iterable<Planet> planets = planetRepository.findAll();
        model.addAttribute("planets", planets);
        return "planets";
    }

    @GetMapping("planetsOM")
    public String showPlanetsOM(Model model) {
        Iterable<Planet> planets = planetRepository.findAll();
        model.addAttribute("planets", planets);
        return "planets";
    }

    @GetMapping("moons")
    public String showMoons(Model model) {
        Iterable<Moon> moons = moonRepository.findAll();
        model.addAttribute("moons", moons);
        return "moons";
    }

    @GetMapping("galaxies")
    public String showGalaxies(Model model) {
        Iterable<Galaxy> galaxies = galaxyRepository.findAll();
        Iterable<MoonMO> moons = moonMORepository.findAll();
        model.addAttribute("galaxies", galaxies);
        //model.addAttribute("moons", moons);
        return "galaxy";


    }

    @GetMapping("moonsMO")
    public String showMoonsMO(Model model) {
        Iterable<MoonMO> moons = moonMORepository.findAll();
        model.addAttribute("moonsMO", moons);
        return "moonsMO";
    }

    @GetMapping("signup")
    public String showSignUpForm(Planet planet) {
        return "add-planet";
    }

    @GetMapping("galaxies/signup")
    public String showSignUpFormGalaxy(Galaxy galaxy, MoonMO moon) {
        return "add-galaxy";
    }

    @GetMapping("planets/signup")
    public String showSignUpFormPlanet(Planet planet) {
        return "add-planet";
    }

    @GetMapping("moonsMO/signup")
    public String showSignUpFormMoonMO(MoonMO moon) {
        return "add-moonMO";
    }

    @GetMapping("moons/signup")
    public String showSignUpFormMoon(Moon moon) {
        return "add-moon";
    }

    @PostMapping("planets/add")
    public String addPlanet(@Valid Planet planet, BindingResult result, Model model){
        if (result.hasErrors()){
            return "add-planet";
        }

        planetRepository.save (planet);
        return "redirect:/solarsystem/planets";
    }

    @PostMapping("moons/add")
    public String addMoon(@Valid Moon moon, BindingResult result, Model model){
        if (result.hasErrors()){
            return "add-moon";
        }

        moonRepository.save (moon);
        return "redirect:/solarsystem/moons";
    }


    @PostMapping("galaxy/add")
    public String addGalaxy(Galaxy galaxy, MoonMO moon, BindingResult result, Model model){
        if (result.hasErrors()){
            return "add-galaxy";
        }

        moon.setGalaxy(galaxy);
        moonMORepository.save (moon);
        galaxyRepository.save (galaxy);
        return "redirect:/solarsystem/galaxy";
    }

    @PostMapping("moonsMO/add")
    public String addMoonMO(@Valid MoonMO moon, BindingResult result, Model model){
        if (result.hasErrors()){
            return "add-moon";
        }

        moonMORepository.save (moon);
        return "redirect:/solarsystem/moonsMO";
    }

    @GetMapping("planets/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Planet planet= planetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid planet Id:" + id));
        model.addAttribute("planet", planet);
        return "update-planet";
    }

    @PostMapping("planets/update/{id}")
    public String updatePlanet(@PathVariable("id") long id, @Valid Planet planet, BindingResult result,
                                Model model) {

        planetValidator.validate(planet);

        if (result.hasErrors()) {
            planet.setId(id);
            return "update-planet";
        }

        planetRepository.save(planet);
//		model.addAttribute("students", studentRepository.findAll());
//		return "index";

//		second option:
//		return "redirect:list";
        return "redirect:/solarsystem/planets";
    }

    @GetMapping("moons/edit/{id}")
    public String showUpdateFormMoon(@PathVariable("id") long id, Model model) {
        Moon moon= moonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid moon Id:" + id));
        model.addAttribute("moon", moon);
        return "update-moon";
    }

    @GetMapping("moonsMO/edit/{id}")
    public String showUpdateFormMoonMO(@PathVariable("id") long id, Model model) {
        MoonMO moon= moonMORepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid moon MO Id:" + id));
        model.addAttribute("moonMO", moon);
        return "update-moonMO";
    }

    @PostMapping("moons/update/{id}")
    public String updateMoon(@PathVariable("id") long id, @Valid Moon moon, BindingResult result,
                               Model model) {

        moonValidator.validate(moon);

        if (result.hasErrors()) {
            moon.setId(id);
            return "update-moon";
        }

        moonRepository.save(moon);
//		model.addAttribute("students", studentRepository.findAll());
//		return "index";

//		second option:
//		return "redirect:list";
        return "redirect:/solarsystem/moons";
    }

    @GetMapping("planets/delete/{id}")
    public String deletePlanet(@PathVariable("id") long id, Model model) {
        Planet planet  = planetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid planet Id:" + id));
        planetRepository.delete(planet);
        model.addAttribute("planets", planetRepository.findAll());
        return "redirect:/solarsystem/planets";
    }

    @GetMapping("moons/delete/{id}")
    public String deleteMoon(@PathVariable("id") long id, Model model) {
        Moon moon  = moonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid moon Id:" + id));
        moonRepository.delete(moon);
        model.addAttribute("moons", moonRepository.findAll());
        return "redirect:/solarsystem/moons";
    }

    @GetMapping("moonsMO/delete/{id}")
    public String deleteMoonMO(@PathVariable("id") long id, Model model) {
        MoonMO moon  = moonMORepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid moonMO Id:" + id));
        moonMORepository.delete(moon);
        model.addAttribute("moonsMO", moonMORepository.findAll());
        return "redirect:/solarsystem/moonsMO";
    }
}
