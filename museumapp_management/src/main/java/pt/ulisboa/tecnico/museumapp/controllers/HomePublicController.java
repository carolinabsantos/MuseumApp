package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pt.ulisboa.tecnico.museumapp.entities.*;
import pt.ulisboa.tecnico.museumapp.service.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    @GetMapping("/start-visit") public RedirectView startVisit() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://192.168.1.121:8000/");
        return redirectView;
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

    private static final String ROOM_PLAN_IMAGE_PATH = "./src/main/resources/static/room-plans/";
    @GetMapping("/visit-info")
    public ModelAndView visitInfoView(@RequestParam("visit_id") String visit_id) throws IOException {
        Integer visitId = Integer.valueOf(visit_id);
        VisitEntity visit = visitService.findVisit(visitId).get();
        TimeSlotEntity ts = timeSlotService.findTimeSlot(visit.getTimeSlotId()).get();
        String tsName = ts.getName();
        String roomPlanName = visit.getTimeMachine().getImage();
        String []formatSplit=roomPlanName.split("\\.",2);
        String format = formatSplit[1];

        BufferedImage bufferedImage= ImageIO.read(new File(ROOM_PLAN_IMAGE_PATH + roomPlanName));
        String imagePath= timeMachineService.encodeToString(bufferedImage,format);

        ModelAndView mav = new ModelAndView("public/visit-info");
        mav.addObject("path", imagePath);
        mav.addObject("tsname", tsName);
        mav.addObject("visit", visit);
        return mav;
    }
    @PostMapping("/started-visit")
    public String startedVisit(@RequestParam("visit_id") Integer visitId, RedirectAttributes redirectAttributes) {
        VisitEntity visit = visitService.findVisit(visitId).get();
        TimeSlotEntity timeSlot = timeSlotService.findTimeSlot(visit.getTimeSlotId()).get();
        System.out.println("Got to started-visit!");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime visitTimeEnd = new java.sql.Timestamp(
                timeSlot.getEndTime().getTime()).toLocalDateTime();
        String start_time = dtf.format(now);
        redirectAttributes.addFlashAttribute("start_time", "Time now:" + start_time);
        redirectAttributes.addFlashAttribute("visitTimeEnd", "Time now:" + visitTimeEnd);
        if(now.isAfter(visitTimeEnd)){
            System.out.println("now.isAfter(visitTimeEnd)");
            if(visit.getState() == State.TO_START){
                visitService.updateVisitObservations(visitId, "Missed");
                return "public/error-start-visit";
            }
            if(visit.getState() != State.TO_START){
                visitService.updateVisitObservations(visitId, "Error occrured");
                return "public/error-start-visit";
            }
        }

        if(visit.getState() != State.TO_START) {
            return "public/error-start-visit";
        }
        visitService.startVisit(visitId, start_time);
        return "redirect:/museum";
    }
}
