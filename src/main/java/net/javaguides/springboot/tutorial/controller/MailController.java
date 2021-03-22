package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.email.EmailServiceImpl;
import net.javaguides.springboot.tutorial.entity.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/mail")
@Controller
public class MailController {
    @Autowired
    EmailServiceImpl emailService;

    private static Logger LOG = LoggerFactory.getLogger(AddProductController.class);

    private ModelAndView getFormMAV (@Valid Mail mail){
        ModelAndView mav = new ModelAndView("mail/create");
        mav.addObject("mail", mail);
        return mav;
    }

    @GetMapping
    public ModelAndView add () {
        return getFormMAV(new Mail());
    }

    @PostMapping("/send")
    public ModelAndView add(@Valid Mail mail, BindingResult bindingResult, Model model){
        LOG.info("Mail received {}", mail);

        if (bindingResult.hasErrors()){
            return getFormMAV(mail);
        } else {
            String email = mail.getReceiver();

            emailService.sendSimpleMessage(email, mail.getTitle(), "" +
//                "Witaj w serwisie. Kliknij w link aktywacyjny: https://test8205.herokuapp.com/activate?hash=" + passwordCrypt + "&user=" + name);
                    mail.getDescription());

                return new ModelAndView ("redirect:/");


        }

    }
}
