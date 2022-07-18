package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;
import pt.ulisboa.tecnico.museumapp.models.TimeMachine;
import pt.ulisboa.tecnico.museumapp.models.Visit;
import pt.ulisboa.tecnico.museumapp.service.TimeMachineService;
import pt.ulisboa.tecnico.museumapp.service.VisitService;
import pt.ulisboa.tecnico.museumapp.service.VisitorService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;


@Controller // This means that this class is a Controller
public class VisitController implements WebMvcConfigurer{
    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private TimeMachineService timeMachineService;

    @GetMapping("/book-visit")
    public ModelAndView bookVisitView(@RequestParam Integer visitor_id) {
        ModelAndView mav = new ModelAndView("book-visit");
        Visit visit = new Visit(visitor_id);
        mav.addObject("visit", visit);
        mav.addObject("timeMachines", timeMachineService.getAllTimeMachines());
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
        VisitEntity v = visitService.findVisit(visit_id).get();
        TimeMachineEntity t = timeMachineService.findTimeMachineByName(v.getTimeMachine().getName()).get();
        TimeMachine timeMachine = new TimeMachine(t.getType(), t.getName(), t.getCapacity());
        Visit visit = new Visit(v.getId(), timeMachine, v.getStartTime(), v.getEndTime(), v.getState(), v.getVisitor().getId(), v.getObservations(), v.getVisitDate());
        mav.addObject("visit", visit);
        return mav;
    }

    @PostMapping("/save-visit")
    public String saveVisit(@ModelAttribute Visit visit) {
        TimeMachineEntity timeMachine= timeMachineService.findTimeMachineByName(visit.getTimeMachine().getName()).get();
        VisitorEntity visitor = visitorService.findVisitor(visit.getVisitorId()).get();
        String observations;
        if(visit.getObservations()==null){
            observations="No observations.";
        } else {
            observations=visit.getObservations();
        }
        VisitEntity visitFinal = new VisitEntity(timeMachine, visitor, observations, visit.getVisitDate());
        visitService.createVisit(visitFinal);
        visit.setId(visitFinal.getId());
        return "saved-visit";
    }

    @PostMapping("/save-observations")
    public String saveObservations(@ModelAttribute Visit visit) {
        VisitEntity v = visitService.findVisit(visit.getId()).get();
        VisitEntity visitFinal = new VisitEntity(v.getTimeMachine(), v.getVisitor(), visit.getObservations(), v.getVisitDate());
        visitService.deleteVisit(v.getId());
        visitService.createVisit(visitFinal);
        return "redirect:/list-visits";
    }

    @GetMapping("/delete-visit/{id}")
    public String deleteVisit(@PathVariable(value = "id", required = false) Integer visitId) {
        VisitEntity visit = visitService.findVisit(visitId).get();
        visitService.deleteVisit(visitId);
        visitorService.deleteVisitor(visit.getVisitor().getId());//when delting a visit, the corresponding visitor is also deleted
        return "redirect:/list-visits";
    }
}
