package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.entity.Seat;
import net.javaguides.springboot.tutorial.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.slf4j.LoggerFactory;

//import java.util.logging.Logger;

@RequestMapping("/event/add")
@Controller
public class AddEventController {

    private static Logger LOG = LoggerFactory.getLogger(AddEventController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private EventService eventService;

    @Autowired
    private SeatService seatService;

    @GetMapping
    public ModelAndView add (){
        return getFormMAV(new Event());
    }

    @PostMapping
    public ModelAndView add(@RequestParam("file") MultipartFile file, @Valid Event event, BindingResult bindingResult){
        LOG.info("File received {}", file);
        LOG.info("Event received {}", event);

        if (bindingResult.hasErrors()){
            if (file.isEmpty()){
                bindingResult.addError(new FieldError("event", "imagePath", "Plik nie moze byc pusty!"));
            }
            return getFormMAV(event);
        } else {
            try {
                String uploadedFile = fileService.store(file);
                LOG.info("File received {}", uploadedFile);
                event.setImagePath(uploadedFile);
                //product.setTimestamp(LocalDateTime.now());
                List <Seat> seats = new ArrayList<>();


                for (int i = 0; i < 4; i ++){
                    Seat seat = new Seat();
                    seat.setStatus("f");
                    seat.setNumber(i+1);
                    seat.setComment("ND");
                    seat.setEvent(event);
                    seat.setPosX(i%2 * 320 + 320);
                    seat.setPosY(i/2 * 160 + 160);
                    seats.add(seat);
                    seatService.save(seat);

                }
                event.setSeats(seats);
                eventService.save(event);

                return new ModelAndView ("redirect:/events/");

            } catch (IOException | FileServiceException e) {
                e.printStackTrace();
                LOG.error("Error during file store", e);

                bindingResult.addError(new FieldError("event",
                        "imagePath",
                        "Problem z wysylka pliku"));

                return getFormMAV(event);
            }
        }

    }

    private ModelAndView getFormMAV (@Valid Event event){
        ModelAndView mav = new ModelAndView("event/add");
        mav.addObject("event", event);
        return mav;
    }
}
