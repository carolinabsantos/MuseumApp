package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;
import pt.ulisboa.tecnico.museumapp.models.Visit;
import pt.ulisboa.tecnico.museumapp.service.TimeMachineService;
import pt.ulisboa.tecnico.museumapp.service.VisitService;
import pt.ulisboa.tecnico.museumapp.service.VisitorService;


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

    @PostMapping("/save-visit")
    public String saveVisit(@ModelAttribute Visit visit) {
        TimeMachineEntity timeMachine= timeMachineService.findTimeMachineByName(visit.getTimeMachine().getName()).get();
        VisitorEntity visitor = visitorService.findVisitor(visit.getVisitorId()).get();
        VisitEntity visitFinal = new VisitEntity(timeMachine, visitor);
        visitService.createVisit(visitFinal);
        return "saved-visit";
    }
    @GetMapping("/delete-visit/{id}")
    public String deleteVisit(@PathVariable(value = "id", required = false) Integer visitId) {
        VisitEntity visit = visitService.findVisit(visitId).get();
        visitService.deleteVisit(visitId);
        visitorService.deleteVisitor(visit.getVisitor().getId());//when delting a visit, the corresponding visitor is also deleted
        return "redirect:/list-visits";
    }
}
