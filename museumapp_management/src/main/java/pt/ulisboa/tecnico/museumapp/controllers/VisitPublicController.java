package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.RedirectView;
import pt.ulisboa.tecnico.museumapp.entities.*;
import pt.ulisboa.tecnico.museumapp.models.*;
import pt.ulisboa.tecnico.museumapp.service.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller // This means that this class is a Controller
public class VisitPublicController implements WebMvcConfigurer{
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private TimeMachineService timeMachineService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private QRCodeService qrCodeService;


    private static final String ROOM_PLAN_IMAGE_PATH = "./src/main/resources/static/room-plans/";

    @GetMapping("/new-public-visitor")
    public String createVisitorForm(Model model) {
        model.addAttribute("visitor", new Visitor());
        return "public/new-public-visitor";
    }

    @PostMapping("/save-visitor-public")
    public RedirectView savePublicVisitor(@ModelAttribute Visitor visitor) {
        VisitorEntity visitorFinal = new VisitorEntity(visitor.getfName(), visitor.getlName(), visitor.getEmail_address(), visitor.getContact(), visitor.getNoVisitors());
        visitorService.createVisitor(visitorFinal);
        visitor.setId(visitorFinal.getId());
        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/book-public-visit?visitor_id=" + visitorFinal.getId());
        return rv;
    }

    @GetMapping("/book-public-visit")
    public ModelAndView bookPublicVisit(@RequestParam Integer visitor_id) {
        ModelAndView mav = new ModelAndView("public/book-public-visit");
        Visit visit = new Visit(visitor_id);
        mav.addObject("visitorId", visitor_id);
        mav.addObject("visit", visit);
        mav.addObject("timeMachines", timeMachineService.getAllTimeMachines());
        return mav;
    }

    @PostMapping("/save-public-visit-info")
    public RedirectView savePublicVisitInfo(@ModelAttribute Visit visit) {
        String observations;
        if(visit.getObservations()==null){
            observations="No observations.";
        } else {
            observations=visit.getObservations();
        }
        VisitorEntity visitor = visitorService.findVisitor(visit.getVisitorId()).get();
        TimeMachineEntity timeMachine= timeMachineService.findTimeMachineByName(visit.getTimeMachine().getName()).get();
        VisitEntity visitFinal = new VisitEntity(timeMachine, visitor, observations, visit.getVisitDate());
        visitService.createVisit(visitFinal);
        visit.setId(visitFinal.getId());
        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("book-visit-timeSlot-public?visit_id=" + visitFinal.getId());
        return rv;
    }

    @GetMapping("/book-visit-timeSlot-public")
    public ModelAndView bookPublicVisitDateView(@RequestParam Integer visit_id) {
        ModelAndView mav = new ModelAndView("public/book-visit-timeSlot-public");
        VisitEntity visit = visitService.findVisit(visit_id).get();
        List<TimeSlotEntity> timeSlots = timeSlotService.findTimeSlotByVisit(visit_id);
        Iterable<TimeSlotEntity> iterableTimeSlots = timeSlots;
        mav.addObject("visit", visit);
        mav.addObject("timeSlots", iterableTimeSlots);
        return mav;
    }
    @PostMapping("/save-visit-timeSlot-public")
    public RedirectView savePublicVisit(@ModelAttribute Visit visit, @ModelAttribute TimeSlot timeSlot) {
        VisitEntity visitBefore = visitService.findVisit(visit.getId()).get();

        VisitorEntity visitor = visitorService.findVisitor(visitBefore.getVisitor().getId()).get();
        visitor.setVisit(null);
        TimeMachineEntity timeMachine= timeMachineService.findTimeMachineByName(visitBefore.getTimeMachine().getName()).get();
        String observations = visitBefore.getObservations();
        Integer timeSlotId = visit.getTimeSlot();

        visitService.deleteVisit(visitBefore);
        Integer tsId = timeSlotService.updateTimeSlot(timeSlotId, visitor.getId());

        VisitEntity visitFinal = new VisitEntity(timeMachine, visitor, tsId, observations, visit.getVisitDate());
        visitService.createVisit(visitFinal);
        visitFinal.setExhibitors();

        visitor.setVisit(visitFinal);
        visit.setTimeMachine(new TimeMachine(visitFinal.getTimeMachine().getType(), visitFinal.getTimeMachine().getName(), visitFinal.getTimeMachine().getCapacity()));
        visit.setTimeSlot(visitFinal.getTimeSlotId());
        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("saved-public-visit-final?visit_id=" + visitFinal.getId());
        return rv;
    }

    @GetMapping("/saved-public-visit-final")
    public ModelAndView savedPublicVisit(@RequestParam Integer visit_id) throws IOException {
        ModelAndView mav = new ModelAndView("public/saved-public-visit-final");
        VisitEntity visit = visitService.findVisit(visit_id).get();

        QRCodeEntity qrCodeEntity = new QRCodeEntity(visit_id);
        QRCodeResult qrCodeResult = qrCodeService.generateQrCodeUrl(qrCodeEntity);
        qrCodeEntity.setQrCode(qrCodeResult.getImage());
        qrCodeService.createQRCode(qrCodeEntity);
        QRCodeResult result = qrCodeService.generateQrCodeUrl(qrCodeEntity);

        String roomPlanName = visit.getTimeMachine().getImage();
        String []formatSplit=roomPlanName.split("\\.",2);
        String format = formatSplit[1];
        BufferedImage bufferedImage= ImageIO.read(new File(ROOM_PLAN_IMAGE_PATH + roomPlanName));
        String imagePath= timeMachineService.encodeToString(bufferedImage,format);
        mav.addObject("visit", visit);
        mav.addObject("qrCode", result.getImage());
        mav.addObject("path", imagePath);
        return mav;
    }

}
