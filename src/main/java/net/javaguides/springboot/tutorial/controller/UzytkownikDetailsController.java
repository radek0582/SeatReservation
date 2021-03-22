package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Comment;
import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.entity.Uzytkownik;
import net.javaguides.springboot.tutorial.service.ProductNotFoundException;
import net.javaguides.springboot.tutorial.service.ProductService;
import net.javaguides.springboot.tutorial.service.UzytkownikNotFoundException;
import net.javaguides.springboot.tutorial.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/users")
@Controller
public class UzytkownikDetailsController {

    @Autowired
    private UzytkownikService uzytkownikServiceImpl;

    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable("id") Long id){

        if (id == null || id <0)
            return redirect();
        else {
            try {
                Uzytkownik uzytkownik = uzytkownikServiceImpl.findById(id);
                ModelAndView mav = new ModelAndView("users/details");
                mav.addObject("uzytkownik", uzytkownik);
                return mav;
            } catch (UzytkownikNotFoundException e) {
                return redirect();
            }
        }
    }

    @GetMapping("/delete/{idUser}")
    public ModelAndView deleteUser(@PathVariable("idUser") long id) {
        Uzytkownik uzytkownik = null;

        try {
            uzytkownik = uzytkownikServiceImpl.findById(id);
        } catch (UzytkownikNotFoundException e) {
            e.printStackTrace();
        }

//                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        uzytkownikServiceImpl.delete(uzytkownik);
//        model.addAttribute("clients", commentServiceImpl.findAll());
//        return "redirect:/product/details";
        return new ModelAndView("redirect:/users/");
    }

    private ModelAndView redirect (){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }

    @GetMapping("/panel")
    public ModelAndView userPanel (){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/users/panel");
        return mav;
    }
}
