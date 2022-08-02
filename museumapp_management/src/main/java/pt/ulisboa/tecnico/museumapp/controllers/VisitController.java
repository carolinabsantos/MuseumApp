package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.models.QRCodeResult;
import pt.ulisboa.tecnico.museumapp.service.*;
import pt.ulisboa.tecnico.museumapp.email.EmailService;
import pt.ulisboa.tecnico.museumapp.entities.*;
import pt.ulisboa.tecnico.museumapp.models.TimeMachine;
import pt.ulisboa.tecnico.museumapp.models.TimeSlot;
import pt.ulisboa.tecnico.museumapp.models.Visit;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;


@Controller // This means that this class is a Controller
public class VisitController implements WebMvcConfigurer{
    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private TimeMachineService timeMachineService;
    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private QRCodeService qrCodeService;


    @GetMapping("/book-visit")
    public ModelAndView bookVisitView(@RequestParam Integer visitor_id) {
        ModelAndView mav = new ModelAndView("book-visit");
        Visit visit = new Visit(visitor_id);
        mav.addObject("visit", visit);
        mav.addObject("timeMachines", timeMachineService.getAllTimeMachines());
        return mav;
    }

    @GetMapping("/book-visit-date")
    public ModelAndView bookVisitDateView(@RequestParam Integer visit_id) {
        ModelAndView mav = new ModelAndView("book-visit-date");
        VisitEntity visit = visitService.findVisit(visit_id).get();
        List<TimeSlotEntity> timeSlots = timeSlotService.findTimeSlotByVisit(visit_id);
        Iterable<TimeSlotEntity> iterableTimeSlots = timeSlots;
        mav.addObject("visit", visit);
        mav.addObject("timeSlots", timeSlots);
        return mav;
    }

    @GetMapping("/list-visits")
    public ModelAndView findAllVisits() {
        ModelAndView mav = new ModelAndView("list-visit");
        mav.addObject("visits", visitService.getAllVisits());
        return mav;
    }

    @GetMapping("/add-observations")
    public ModelAndView addObservations(@RequestParam Integer visit_id) {
        ModelAndView mav = new ModelAndView("add-observations");
        Visit visit = visitService.createVisitFromId(visit_id);
        mav.addObject("visit", visit);
        return mav;
    }
    @PostMapping("/save-visit-info")
    public String saveVisitInfo(@ModelAttribute Visit visit) {
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
        return "saved-visit";
    }

    @PostMapping("/save-visit-date")
    public String saveVisit(@ModelAttribute Visit visit, @ModelAttribute TimeSlot timeSlot) throws Exception {
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

        visitor.setVisit(visitFinal);
        visit.setTimeMachine(new TimeMachine(visitFinal.getTimeMachine().getType(), visitFinal.getTimeMachine().getName(), visitFinal.getTimeMachine().getCapacity()));
        visit.setTimeSlot(visitFinal.getTimeSlotId());

        QRCodeEntity qrCodeEntity = new QRCodeEntity(visitFinal.getId());
        QRCodeResult qrCodeResult = qrCodeService.generateQrCodeUrl(qrCodeEntity);
        qrCodeEntity.setQrCode(qrCodeResult.getImage());
        qrCodeService.createQRCode(qrCodeEntity);

        return "saved-visit-final";
    }

    @PostMapping("/save-observations")
    public String saveObservations(@ModelAttribute VisitEntity visit) {
        VisitEntity v = visitService.findVisit(visit.getId()).get();

        VisitEntity visitFinal = new VisitEntity(v.getTimeMachine(), v.getVisitor(), visit.getObservations(), v.getVisitDate());
        visitService.deleteVisit(v);
        visitService.createVisit(visitFinal);
        return "redirect:/list-visits";
    }

    @GetMapping("/delete-visit/{id}")
    public String deleteVisit(@PathVariable(value = "id", required = false) Integer visitId) {
        VisitEntity visit = visitService.findVisit(visitId).get();
        TimeSlotEntity ts = timeSlotService.findTimeSlot(visit.getTimeSlotId()).get();
        Integer qrCodeId = qrCodeService.getQRCodeByVisit(visitId).getId();
        ts.setCapacity(ts.getCapacity()+visit.getVisitor().getNoVisitors());//when delting a visit, the corresponding capacity of the timeSlot is incremented
        visitService.deleteVisit(visit);
        visitorService.deleteVisitor(visit.getVisitor().getId());//when delting a visit, the corresponding visitor is also deleted
        qrCodeService.deleteQRCode(qrCodeId);
        qrCodeService.deleteQRCodeFile(visitId);
        return "redirect:/list-visits";
    }
}
