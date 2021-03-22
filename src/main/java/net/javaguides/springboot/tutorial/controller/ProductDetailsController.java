package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Comment;
import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.service.ProductNotFoundException;
import net.javaguides.springboot.tutorial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductDetailsController {

    @Autowired
    private ProductService productServiceImpl;

    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable("id") Long id){

        if (id == null || id <0)
            return redirect();
        else {
            try {
                Product product = productServiceImpl.findById(id);
                ModelAndView mav = new ModelAndView("product/details");
                mav.addObject("product", product);
                mav.addObject("comment", new Comment());
                return mav;
            } catch (ProductNotFoundException e) {
                return redirect();
            }
        }
    }

    @GetMapping("random")
    public ModelAndView findRandom() throws ProductNotFoundException {
        try{
            Product product = productServiceImpl.findRandom();
            ModelAndView mav = new ModelAndView("product/details");
            mav.addObject("product",product);
            mav.addObject("comment", new Comment());
            return mav;
        } catch (ProductNotFoundException e){
            return redirect();
        }
    }

    @GetMapping("/delete/{idProd}")
    public ModelAndView deleteComment(@PathVariable("idProd") long id) {
        Product product = null;

        try {
            product = productServiceImpl.findById(id);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }

//                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        productServiceImpl.delete(product);
//        model.addAttribute("clients", commentServiceImpl.findAll());
//        return "redirect:/product/details";
        return new ModelAndView("redirect:/");
    }

    private ModelAndView redirect (){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }
}
