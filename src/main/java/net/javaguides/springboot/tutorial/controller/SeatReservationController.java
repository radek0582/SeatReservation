package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Seat;
import net.javaguides.springboot.tutorial.service.EventNotFoundException;
import net.javaguides.springboot.tutorial.service.EventService;
import net.javaguides.springboot.tutorial.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/events/event/edit/")
@Controller
public class SeatReservationController {

    @Autowired
    private EventService eventServiceImpl;

    @Autowired
    private SeatService seatService;

    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable("id") Long id, Model model){
        int i = 0;

        if (id == null || id <0)
            return redirect();
        else {
            try {
                Event event = eventServiceImpl.findById(id);
                List<Seat> seats = seatService.getSeatListByEvent(event);
//                List<Seat> sortedSeats = new ArrayList<>();

                ModelAndView mav = new ModelAndView("event/seatreservation");
                mav.addObject("event", event);
//                mav.addObject("comment", new Comment());

//                sortedSeats = seats.stream()
//                        .sorted(Comparator.comparing(seat -> seat.getNumber()))
//                        .collect(Collectors.toCollection(ArrayList::new));

                for (i = 0; i < seats.size(); i++) {
                    mav.addObject("seatStatus_" + (i + 1), seats.get(i).getStatus());
                    mav.addObject("seatNumber_" + (i + 1), seats.get(i).getNumber());
                    mav.addObject("seatPosX_" + (i + 1), seats.get(i).getPosX());
                    mav.addObject("seatPosY_" + (i + 1), seats.get(i).getPosY());
                    mav.addObject("seatComment_" + (i + 1), seats.get(i).getComment());
                }
                mav.addObject("eventId", id);

                System.out.println(" - > przed wyst.");

                List <Seat> allSeats = new ArrayList<>();
                seats.forEach(allSeats::add);
                model.addAttribute("allSeats", allSeats);

                System.out.println(" - > po wyst. - > ");
//                mav.addObject("allSeats",seats);

                return mav;
            } catch (EventNotFoundException e) {
                return redirect();
            }
        }

    }

//    @ResponseBody
//    @RequestMapping(value = "reserve/{eventId}/{s1n}/{s1f}/{s1c}/{s1X}/{s1Y}/{s2n}/{s2f}/{s2c}/{s2X}/{s2Y}/{s3n}/{s3f}/{s3c}/{s3X}/{s3Y}/{s4n}/{s4f}/{s4c}/{s4X}/{s4Y}")
//    public void getSearchResultViaAjax(@PathVariable(value = "eventId") int id,
//                                       @PathVariable(value ="s1n") int seat1num,
//                                       @PathVariable(value ="s1f") String seat1free,
//                                       @PathVariable(value ="s1c") String seat1comment,
//                                       @PathVariable(value ="s1X") int seat1posX,
//                                       @PathVariable(value ="s1Y") int seat1posY,
//                                       @PathVariable(value ="s2n") int seat2num,
//                                       @PathVariable(value ="s2f") String seat2free,
//                                       @PathVariable(value ="s2c") String seat2comment,
//                                       @PathVariable(value ="s2X") int seat2posX,
//                                       @PathVariable(value ="s2Y") int seat2posY,
//                                       @PathVariable(value ="s3n") int seat3num,
//                                       @PathVariable(value ="s3f") String seat3free,
//                                       @PathVariable(value ="s3c") String seat3comment,
//                                       @PathVariable(value ="s3X") int seat3posX,
//                                       @PathVariable(value ="s3Y") int seat3posY,
//                                       @PathVariable(value ="s4n") int seat4num,
//                                       @PathVariable(value ="s4f") String seat4free,
//                                       @PathVariable(value ="s4c") String seat4comment,
//                                       @PathVariable(value ="s4X") int seat4posX,
//                                       @PathVariable(value ="s4Y") int seat4posY
//    ) throws EventNotFoundException {
//
//        Long eventId= new Long(id);
//
//
////        System.out.println(params);
//        System.out.println(eventId);
//
//        Event event = eventServiceImpl.findById(eventId);
//        List<Seat> seats = seatService.getEventList(event);
//
//        for (int i = 0; i < seats.size(); i++){
//            Seat seat = seats.get(i);
//
//            if (seats.get(i).getNumber() == 1){
//                seat.setNumber(seat1num);
//                seat.setPosX(seat1posX);
//                seat.setPosY(seat1posY);
//                seat.setComment(seat1comment);
//                seat.setStatus(seat1free);
//            }
//            else if (seats.get(i).getNumber() == 2){
//                seat.setNumber(seat2num);
//                seat.setPosX(seat2posX);
//                seat.setPosY(seat2posY);
//                seat.setComment(seat2comment);
//                seat.setStatus(seat2free);
//            }
//            else if (seats.get(i).getNumber() == 3){
//                seat.setNumber(seat3num);
//                seat.setPosX(seat3posX);
//                seat.setPosY(seat3posY);
//                seat.setComment(seat3comment);
//                seat.setStatus(seat3free);
//            }
//            else if (seats.get(i).getNumber() == 4){
//                seat.setNumber(seat4num);
//                seat.setPosX(seat4posX);
//                seat.setPosY(seat4posY);
//                seat.setComment(seat4comment);
//                seat.setStatus(seat4free);
//            }
//
//            seatService.save(seat);
//        }
//    }

    @ResponseBody
    @RequestMapping(value = "reserve/{eventId}/{seatsAmt}/{params}")
    public void getSearchResultViaAjax(@PathVariable(value = "eventId") int id,
                                       @PathVariable(value = "seatsAmt") int seatsAmount,
                                       @PathVariable(value = "params") String params ) throws EventNotFoundException {

        String[] split = params.split("_");

        Long eventId= new Long(id);
        int currentSeat = 1;
        int charId = 0, currentSeatNumber = 0, a;
        String c = "";

        Event event = eventServiceImpl.findById(eventId);
        List<Seat> seats = seatService.getSeatListByEvent(event);
        System.out.println(" -> -> parametry z frontu -> ->: " + params);

        while (currentSeat <= seatsAmount){
            String [] number = new String[5];
            a = 0;
            c = String.valueOf(params.charAt(charId));
            int seatNumber = 0, seatPosX = 0 , seatPosY = 0;
            String seatComment = "", seatFree = null;

            while (c.equals("_") == false){
                number [a] = c;
                a++;
                charId ++;
                c = String.valueOf(params.charAt(charId));
            }
            charId ++;

            for (int i=0; i<a; i++){
                seatNumber += Integer.parseInt(number[i]) * Math.pow(10, a - i - 1);
            }
            System.out.println(" -> seat number -> " + seatNumber);

            seatFree = String.valueOf(params.charAt(charId));
            System.out.println(" -> seat status -> " + seatFree);
            charId++;
            charId++;
            c = String.valueOf(params.charAt(charId));

            while (c.equals("_") == false){
                seatComment += c;
                charId++;
                c = String.valueOf(params.charAt(charId));
            }
            charId++;
            System.out.println(" -> seat comment -> " + seatComment);

            a = 0;
            String [] number2 = new String [5];
            c = String.valueOf(params.charAt(charId));

            while (c.equals("_") == false){
                number2 [a] = c;
                a++;
                charId ++;
                c = String.valueOf(params.charAt(charId));
            }
            charId ++;

            for (int i=0; i<a; i++){
                seatPosX += Integer.parseInt(number2[i]) * Math.pow(10, a - i - 1);
            }
            System.out.println(" -> seat posX -> " + seatPosX);

            a = 0;
            String [] number3 = new String [5];
            c = String.valueOf(params.charAt(charId));

            while (c.equals("_") == false){
                number3 [a] = c;
                a++;
                charId ++;
                c = String.valueOf(params.charAt(charId));
            }
            charId ++;

            for (int i=0; i<a; i++){
                seatPosY += Integer.parseInt(number3[i]) * Math.pow(10, a - i - 1);
            }
            System.out.println(" -> seat posY -> " + seatPosY);

            for (int i = 0; i < seats.size(); i++){
                Seat seat = seats.get(i);

                if (seat.getNumber() == seatNumber){
                    seat.setNumber(seatNumber);
                    seat.setPosX(seatPosX);
                    seat.setPosY(seatPosY);
                    seat.setComment(seatComment);
                    seat.setStatus(seatFree);
                    seatService.save(seat);
                }
            }

            if (seats.size() < currentSeat){
                Seat seat1 = new Seat();
                seat1.setNumber(seatNumber);
                seat1.setPosX(seatPosX);
                seat1.setPosY(seatPosY);
                seat1.setComment(seatComment);
                seat1.setStatus(seatFree);
                seat1.setEvent(event);
                seatService.save(seat1);
            }

            currentSeat ++;
        }
    }


    private ModelAndView redirect (){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }
}
