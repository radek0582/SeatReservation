package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Uzytkownik;
import net.javaguides.springboot.tutorial.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/users/")
@Controller
public class UzytkownikController {

    @Autowired
    private UzytkownikService uzytkownikServiceImpl;

    @GetMapping
    public ModelAndView list(HttpSession httpSession) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("users/list");

        if (httpSession.getAttribute("userIsAdmin") != null && httpSession.getAttribute("userIsAdmin").equals(true))
            mav.addObject("list", uzytkownikServiceImpl.getRegisteredUsers());      // TODO: zmiana nazwy shift + F6
        else {
            System.out.println("Brak dostepu!");
            mav.addObject("list", null);
        }

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "search/api/getSearchResult/{id}")
    public void getSearchResultViaAjax(@PathVariable(value = "id") Integer id)
    {
        System.out.println(id);

        //return String.valueOf(id);
    }

    @GetMapping ("listby/{sortingMethod}")
    public ModelAndView listByName(@PathVariable("sortingMethod") String sortingMethod){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("users/list");

        List<Uzytkownik> users = uzytkownikServiceImpl.getRegisteredUsers();
        List<Uzytkownik> sortedUsers = new ArrayList<>();

        if (sortingMethod.equals("name")) {
            sortedUsers = users.stream()
                    .sorted(Comparator.comparing(Uzytkownik::getName))
                    .collect(Collectors.toCollection(ArrayList::new));
        }


        mav.addObject("list", sortedUsers);

        return mav;
    }

    private static boolean isNot(Uzytkownik uzytkownik, String name){
//        System.out.println("i'm here");
//        System.out.println("opis prod: " + uzy.getDescription() + "nazwa prod: " + product.getName());

        return !uzytkownik.getName().equals(name);
    }
}
