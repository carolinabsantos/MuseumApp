package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pt.ulisboa.tecnico.museumapp.entities.*;
import pt.ulisboa.tecnico.museumapp.service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomePublicController implements WebMvcConfigurer {

    @Autowired
    private VisitService visitService;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private TimeMachineService timeMachineService;

    @GetMapping("/museum") public String homePublic() {
        return "public/index";
    }

    @GetMapping("/confirm-start-visit") public String confirmStartVisit() {
        return "public/confirm-start-visit";
    }

    @GetMapping("/start-visit") public String startVisit() {
        return "public/start-visit";
    }

    @GetMapping("/login") public String login() {
        return "public/error";
    }
    @GetMapping("/about") public String about() {
        return "public/error";
    }
    @GetMapping("/visits") public String visits() {
        return "public/error";
    }
    @GetMapping("/contacts") public String contacts() {
        return "public/error";
    }

    @GetMapping("/visit-info/{visit_id}")
    public ModelAndView visitInfoView(@PathVariable(value = "visit_id", required = false) Integer visitId) {
        System.out.println("visitID: " + visitId);
        VisitEntity visit = visitService.findVisit(visitId).get();
        ModelAndView mav = new ModelAndView("public/visit-info");
        mav.addObject("visit", visit);
        return mav;
    }
    @PostMapping("/started-visit") public String startedVisit(@RequestParam Integer visitId, RedirectAttributes redirectAttributes) {
        VisitEntity visit = visitService.findVisit(visitId).get();
        TimeSlotEntity timeSlot = timeSlotService.findTimeSlot(visit.getTimeSlotId()).get();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime visitTimeEnd = new java.sql.Timestamp(
                timeSlot.getEndTime().getTime()).toLocalDateTime();
        String start_time = dtf.format(now);
        redirectAttributes.addFlashAttribute("start_time", "Time now:" + start_time);
        redirectAttributes.addFlashAttribute("visitTimeEnd", "Time now:" + visitTimeEnd);
        if(now.isAfter(visitTimeEnd) && (visit.getState() == State.TO_START)){
            visitService.updateVisitObservations(visitId, "Missed");
            return "public/error-start-visit";
        }
        if(now.isAfter(visitTimeEnd) && (visit.getState() != State.TO_START)){
            visitService.updateVisitObservations(visitId, "Error occrured");
            return "public/error-start-visit";
        }
        if(visit.getState() != State.TO_START) {
            return "public/error-start-visit";
        }
        visitService.startVisit(visitId, start_time);
        return "redirect:/museum";
    }
    @GetMapping("/available-visits") public String indexAvailableVisits() {
        return "available-visits";
    }
}
