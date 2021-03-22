package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequestMapping("/")
@Controller
public class ProductController {

    @Autowired
    private ProductService productServiceImpl;

    @GetMapping
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("product/list");
        mav.addObject("list", productServiceImpl.getList());

        return mav;
    }

    @GetMapping ("listby/{sortingMethod}")
    public ModelAndView listByName(@PathVariable("sortingMethod") String sortingMethod){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("product/list");

        List<Product> products = productServiceImpl.getList();
        List<Product> sortedProducts = new ArrayList<>();

        if (sortingMethod.equals("name")) {
            sortedProducts = products.stream()
//                    .sorted(Comparator.comparing(Product::getName))
//                    .sorted(Comparator.comparing(product -> product.getName().toLowerCase()))
                    .sorted(Comparator.comparing(product -> product.getName().toLowerCase()))
                    .collect(Collectors.toCollection(ArrayList::new));

//            ArrayList<String> names = products.stream()
//                    .map(product -> product.getName())
//                    .map(name -> name.toLowerCase())
//                    .sorted(Comparator.comparing(lowerCaseName -> lowerCaseName))
//                    .collect(Collectors.toCollection(ArrayList::new));


        }

        if (sortingMethod.equals("mostpopular")) {
            sortedProducts = products.stream()
                    .sorted(Comparator.comparing(Product::getVisitCount)
                    .reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        mav.addObject("list", sortedProducts);

        return mav;
    }

    private static boolean isNot(Product product, String name){
//        System.out.println("i'm here");
        System.out.println("opis prod: " + product.getDescription() + "nazwa prod: " + product.getName());

        return !product.getName().equals(name);
    }
}
