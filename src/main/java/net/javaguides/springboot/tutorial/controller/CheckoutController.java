package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.ChargeRequest;
import net.javaguides.springboot.tutorial.entity.MoneyObj;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }

    @PostMapping("/paying")
    public String paying(@Valid MoneyObj moneyObj, Model model){
        int moneyAmnt = moneyObj.getAmount();

        model.addAttribute("amount", moneyAmnt * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }

    @GetMapping("/payingpage")
    public String showPayingForm(MoneyObj moneyObj) {
        return "paying";
    }
}