package net.javaguides.springboot.tutorial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.springboot.tutorial.entity.State;
import net.javaguides.springboot.tutorial.entity.manytomany.Client;
import net.javaguides.springboot.tutorial.entity.manytomany.Company;
import net.javaguides.springboot.tutorial.entity.manytomany.Continent;
import net.javaguides.springboot.tutorial.repository.ClientRepository;
import net.javaguides.springboot.tutorial.repository.CompanyRepository;
import net.javaguides.springboot.tutorial.repository.ContinentRepository;
import net.javaguides.springboot.tutorial.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping ("/clients/")
public class ClientsController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ContinentRepository continentRepository;

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("list")
    public ModelAndView showClients(Model model) {
        ModelAndView result;
        result = new ModelAndView("clients");

        String msg = "msg1";

        Iterable<Client> clients = clientRepository.findAll();

        model.addAttribute("clients", clients);

        result.addObject("msg", msg);
//        result.addObject("clients", clients);

        return result;
    }




    @GetMapping("signup")
    public String showSignUpForm(Client client, Model model) {


        Iterable<Company> companies = companyRepository.findAll();

        List<Company> result = new ArrayList<Company>();
        companies.forEach(result::add);

        model.addAttribute("companies", result);


        Iterable<Continent> continents = continentRepository.findAll();

        List<Continent> result2 = new ArrayList<Continent>();
        continents.forEach(result2::add);

        model.addAttribute("continents", result2);


        return "add-client";
    }

    @PostMapping("add")
    public String addClient(@Valid Client client, BindingResult result, Model model, @ModelAttribute ("company") String company, @ModelAttribute ("continent") String continent) {
//        if (result.hasErrors()) {
//            System.out.println("dupa, " + client.getName() + " " + company + " " + continent);
//            return "add-client";
//        }

        List<Company> companies = companyRepository.findByName(company);
        client.setCompanies(companies);


        Company company1 = companies.get(0);
//        client.setCompany(company1);

        ArrayList<Client> clients = new ArrayList<>();
        clients.add(client);

        company1.setClients(clients);

        System.out.println(company  + " " +  company1.getName());

        //List<Company> companies= new ArrayList<Company>();
        //companies.add(company);


        List<Continent> continents = continentRepository.findByName(continent);
        client.setContinents(continents);

        Continent continent1 = continents.get(0);
//        client.setContinent(continent1);

        continent1.setClients(clients);

        System.out.println(continent);

        clientRepository.save(client);




        return "redirect:list";
    }


    @GetMapping("client/delete/{id}")
    public String deleteClient(@PathVariable("id") long id, Model model) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        clientRepository.delete(client);
        model.addAttribute("clients", clientRepository.findAll());
        return "redirect:/clients/list";

    }


    @GetMapping("client/edit/{id}")
    public String showUpdateFormClient(@PathVariable("id") long id, Model model) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        model.addAttribute("client", client);

        Iterable<Company> companies = companyRepository.findAll();

        List<Company> result = new ArrayList<Company>();
        companies.forEach(result::add);

        model.addAttribute("companies", result);


        Iterable<Continent> continents = continentRepository.findAll();

        List<Continent> result2 = new ArrayList<Continent>();
        continents.forEach(result2::add);

        model.addAttribute("continents", result2);
        return "update-client";
    }

    @PostMapping("client/update/{id}")
    public String updateClient(@PathVariable("id") long id, @Valid Client client, BindingResult result,
                             Model model, @ModelAttribute ("company") String company, @ModelAttribute ("continent") String continent) {

        if (result.hasErrors()) {
//            client.setId(id);
//            return "update-client";
            System.out.println("update errors: " + result.getAllErrors());
        }

        List<Company> companies = companyRepository.findByName(company);
        client.setCompanies(companies);


        Company company1 = companies.get(0);
//        client.setCompany(company1);

        ArrayList<Client> clients = new ArrayList<>();
        clients.add(client);

        company1.setClients(clients);

        System.out.println(company  + " " +  company1.getName());

        List<Continent> continents = continentRepository.findByName(continent);
        client.setContinents(continents);


        Continent continent1 = continents.get(0);
//        client.setContinent(continent1);

        continent1.setClients(clients);

        System.out.println("update confirmed" + continent);

        clientRepository.save(client);
        return "redirect:/clients/list";
    }


}
