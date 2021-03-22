package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.MD5Crypt.MD5;
import net.javaguides.springboot.tutorial.email.EmailServiceImpl;

import net.javaguides.springboot.tutorial.entity.State;
import net.javaguides.springboot.tutorial.entity.Student;
import net.javaguides.springboot.tutorial.entity.Uzytkownik;
import net.javaguides.springboot.tutorial.repository.StateRepository;
import net.javaguides.springboot.tutorial.repository.UzytkownikRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping
public class AuthorizationController {
    @Autowired
    UzytkownikRepository uzytkownikRepository;

    @Autowired
//    EmailServiceImpl emailService;
            EmailServiceImpl emailService;

    @Autowired
    MD5 md5Password;

    @Value("${myapplication.env}")
    String env;

    @Value("${myapplication.address}")
    String myAppAddress;

    @PostMapping("/login")
    public String login(@Valid Uzytkownik uzytkownik, BindingResult result, RedirectAttributes redirectAttributes, HttpSession httpSession,   Model model) {
//

        System.out.println("logowanie");

        if (result.hasErrors()) {
//            return "loginpage";
            System.out.println(result.getAllErrors().toString());
        }

        String name = uzytkownik.getName();
        String password = uzytkownik.getPassword();
        boolean adminPerm = false;


        List<Uzytkownik> users = uzytkownikRepository.findByName(name);

        if (users.size() > 1){
//            throw new RuntimeException("wiecej niz jeden uzytk. o podanej nazwie:" + name);
//            model.addAttribute("amount", 2);
            return "loginpage";
        }

        if (users.size() == 0){
//            throw new RuntimeException("brak uzytk. o podanej nazwie:" + name);
            return "loginpage";
        }

        Uzytkownik uzytkownik1 = users.get(0);
        httpSession.setAttribute("Language", "EN");


        String passwordCrypt = md5Password.generatedPass(password);

        if ((uzytkownik1.getPassword()).equals(passwordCrypt)){
            if (uzytkownik1.isActive() == false) {
                System.out.println("Aktywuj konto poprzez link aktywacyjny zawarty w emailu.");
                redirectAttributes.addFlashAttribute("notActivated", true);
            }
            else {
                adminPerm = uzytkownik1.isAdmin();

                httpSession.setAttribute("userIsLogged", true);
                httpSession.setAttribute("userName", name);
                httpSession.setAttribute("userIsAdmin", adminPerm);
                System.out.println("user name: " + name + "user pass: " + password + "user perm: " + adminPerm);
            }

            return "redirect:/events/";
        }
        else{
            redirectAttributes.addFlashAttribute("wrongPassword", true);
            return "redirect:/loginpage";
        }


//        return "loginpage";
    }

    @PostMapping("/register")
    public String register(@Valid Uzytkownik uzytkownik, BindingResult result, RedirectAttributes redirectAttributes, HttpSession httpSession,  Model model) {
        boolean errorMessage = false;

        System.out.println("rejestracja");

        if (result.hasErrors()) {
            return "registerpage";
        }
//
//        studentRepository.save(student);
//        return "redirect:list";
        String name = uzytkownik.getName();
        String password = uzytkownik.getPassword();
        boolean admin = uzytkownik.isAdmin();
        String email = uzytkownik.getEmail();

        String passwordCrypt = md5Password.generatedPass(password);
//        System.out.println(passwordCrypt);

        Uzytkownik uzytkownik1 = new Uzytkownik ();

        uzytkownik1.setAdmin(admin);
        uzytkownik1.setName(name);
        uzytkownik1.setPassword(passwordCrypt);
        uzytkownik1.setActive(false);
        uzytkownik1.setEmail(email);

//        List<Uzytkownik> users = uzytkownikRepository.findByName(name);

        List <Uzytkownik> users = uzytkownikRepository.findByEmail(email);
        List <Uzytkownik> users1 = uzytkownikRepository.findByName(name);

        if (isValidEmailAddress(email) == false){
            System.out.println("Bledny adres email!");
            redirectAttributes.addFlashAttribute("wrongEmail", true);
            errorMessage = true;
        }

        if (users.size() > 0) {
            System.out.println("Jest juz taki user z tym mailem");
            redirectAttributes.addFlashAttribute("existingEmail", true);
            errorMessage = true;
        }

        if (users1.size() > 0) {
            System.out.println("Jest juz taki user z ta nazwa");
            redirectAttributes.addFlashAttribute("existingName", true);
            errorMessage = true;
        }

        if (errorMessage)
            return "redirect:/registerpage";

        uzytkownikRepository.save(uzytkownik1);
        String activationLink;

        if (env.equals("dev"))
            activationLink = "http://localhost:9089/activate?hash=";
        else
            activationLink = "https://" + myAppAddress + "/activate?hash=";
//            activationLink = "http://test8205.herokuapp.com/activate?hash=";

        System.out.println(" - > - appAddress: " + activationLink);


            emailService.sendSimpleMessage(email, "Link aktywacyjny", "" +
                    "Witaj w serwisie. Kliknij w link aktywacyjny: " + activationLink + passwordCrypt + "&user=" + name);


//        emailService.sendSimpleMessage("ss", "sds", "ssd", true);

        redirectAttributes.addFlashAttribute("justRegistered", true);
        return "redirect:/events/";
    }

    @GetMapping("/activate")
    public String activateAccount(@RequestParam(name = "hash") String hash, @RequestParam(name = "user") String username,  RedirectAttributes redirectAttributes, HttpSession httpSession) {
        System.out.println("aktywacja konta");

        List<Uzytkownik> users = uzytkownikRepository.findByName(username);

        Uzytkownik uzytkownik1 = users.get(0);

        if (hash.equals(uzytkownik1.getPassword())) {
            System.out.println("haslo prawidlowe");
        }
        else{
            System.out.println("haslo nieprawidlowe");
            redirectAttributes.addFlashAttribute("wrongActivationKey", true);
            return "redirect:/clients/list";
        }

        uzytkownik1.setActive(true);
        uzytkownikRepository.save(uzytkownik1);

        redirectAttributes.addFlashAttribute("justActivated", true);
        return "redirect:/events/";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession httpSession) {
        System.out.println("wylogowanie");

        httpSession.setAttribute("userIsLogged", false);
        httpSession.setAttribute("userIsAdmin", false);
        httpSession.setAttribute("userName", "ND");

        model.addAttribute("uzytkownik",new Uzytkownik());


        return "redirect:/events/";
    }

    @GetMapping("/loginpage")
    public String showSignUpForm(Uzytkownik uzytkownik) {
        return "loginpage";
    }


    @GetMapping("/registerpage")
    public String showRegisterPage(Uzytkownik uzytkownik) {
        return "registerpage";
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    @GetMapping("/resetpassword")
    public String showResetPasswordForm (Uzytkownik uzytkownik){ return "resetpasswordpage";}

    @PostMapping("/resetpasswordcontroller")
    public String resetPassword(@Valid Uzytkownik uzytkownik, BindingResult result, RedirectAttributes redirectAttributes, HttpSession httpSession,   Model model) {
        System.out.println("resetowanie hasla");

        if (result.hasErrors()) {
//            return "loginpage";
            System.out.println(result.getAllErrors().toString());
        }

        String email = uzytkownik.getEmail();

        List <Uzytkownik> users = uzytkownikRepository.findByEmail(email);

        if (users.size() == 0) {
//            redirectAttributes.addFlashAttribute("userNotExist", true);
            System.out.println(" -->> nie ma takiego usera");
            model.addAttribute("userNotExist", true);
            return "resetpasswordpage";
        }

        Uzytkownik user = users.get(0);

        String email1 = user.getEmail();
        String currentpassword = user.getPassword();
        String name = user.getName();

//        String passwordCrypt = md5Password.generatedPass(currentpassword + DateTime.now().dayOfYear().toString());

        String activationLink;

        if (env.equals("dev"))
            activationLink = "http://localhost:9089/resetpasswordlink?hash=";
        else
            activationLink = "https://" + myAppAddress + "/resetpasswordlink?hash=";

//        System.out.println(" - > - appAddress: " + myAppAddress);


            emailService.sendSimpleMessage(email, "Link do ustawienia nowego hasla.", "" +
                    "Kliknij link w celu ustalenia nowego hasla: " + activationLink + currentpassword + "&user=" + name);


        redirectAttributes.addFlashAttribute("passwordResetMessage", true);

        return "redirect:/events/";
    }

    @GetMapping("/resetpasswordlink")
    public String resetPassword(@RequestParam(name = "hash") String hash, @RequestParam(name = "user") String username,  RedirectAttributes redirectAttributes, HttpSession httpSession, Uzytkownik uzytkownik) {
        System.out.println("reset hasla");

        List<Uzytkownik> users = uzytkownikRepository.findByName(username);

        Uzytkownik uzytkownik1 = users.get(0);
        httpSession.setAttribute("userName", uzytkownik1.getName());

        if (hash.equals(uzytkownik1.getPassword())) {
            System.out.println("haslo prawidlowe");
        }
        else{
            System.out.println("haslo nieprawidlowe");
            redirectAttributes.addFlashAttribute("wrongActivationKey", true);
            return "resetpasswordpage";
        }

        return "createpassword";
    }

    @PostMapping("/setnewpassword")
    public String setPassword(@Valid Uzytkownik uzytkownik, BindingResult result, RedirectAttributes redirectAttributes, HttpSession httpSession,   Model model) {
        System.out.println("ustawieni nowego hasla");

        String newPassword = uzytkownik.getPassword();
        String userName = httpSession.getAttribute("userName").toString();

        System.out.println(userName);

        List<Uzytkownik> users = uzytkownikRepository.findByName(userName);

        Uzytkownik user = users.get(0);

        String passwordCrypt = md5Password.generatedPass(newPassword);

        user.setPassword(passwordCrypt);

        uzytkownikRepository.save(user);

        redirectAttributes.addFlashAttribute("passwordChanged", true);
        return "redirect:/loginpage/";
    }
}
