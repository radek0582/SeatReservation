package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Comment;
import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.service.CommentService;
import net.javaguides.springboot.tutorial.service.ProductNotFoundException;
import net.javaguides.springboot.tutorial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RequestMapping ("/comment")
@Controller
public class CommentController {
    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private CommentService commentServiceImpl;

    @PostMapping
    public ModelAndView saveComment(@RequestParam("productId") Long productId, @Valid Comment comment, BindingResult bindingResult) throws ProductNotFoundException {

        Product product = productServiceImpl.findById(productId);

        if (bindingResult.hasErrors()){
            ModelAndView mav = new ModelAndView("product/details");
            mav.addObject("product", product);
            mav.addObject("comment", comment);
            return mav;
        } else{
            comment.setProduct(product);
            comment.setTimestamp(LocalDateTime.now());
            commentServiceImpl.save(comment);
            return new ModelAndView("redirect:product/" + productId);
        }
    }

    @GetMapping("/delete/{idComm}")
    public ModelAndView deleteComment(@PathVariable("idComm") long id) {
        Optional<Comment> opt = commentServiceImpl.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        commentServiceImpl.delete(opt.get());
//        model.addAttribute("clients", commentServiceImpl.findAll());
//        return "redirect:/product/details";
        return new ModelAndView("redirect:/");
    }
}
