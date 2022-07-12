package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;
import pt.ulisboa.tecnico.museumapp.models.Visitor;
import pt.ulisboa.tecnico.museumapp.service.VisitorService;


@Controller // This means that this class is a Controller
public class VisitorController implements WebMvcConfigurer{
    @Autowired
    private VisitorService visitorService;

    @GetMapping("/list-visitor")
    public ModelAndView findAllVisitors() {
        ModelAndView mav = new ModelAndView("list-visitor");
        mav.addObject("visitors", visitorService.getAllVisitors());
        return mav;
    }
    @GetMapping("/new-visitor")
    public String createVisitorForm(Model model) {
        model.addAttribute("visitor", new Visitor());
        model.addAttribute("visits", visitorService.getAllVisits());
        return "new-visitor";
    }
    @PostMapping("/save-visitor")
    public String saveVisitor(@ModelAttribute Visitor visitor) {
        TimeMachineEntity timeMachine = new TimeMachineEntity(visitor.getVisit().getTimeMachine().getType(), visitor.getVisit().getTimeMachine().getName());
        VisitEntity visit = new VisitEntity(visitor.getVisit().getStart_time(), visitor.getVisit().getEnd_time(), visitor.getVisit().getState(), timeMachine);
        VisitorEntity visitorFinal = new VisitorEntity(visitor.getfName(), visitor.getlName(), visitor.getEmail_address(), visitor.getContact(), visitor.getNoVisitors(), visit);
        visitorService.createVisitor(visitorFinal);
        return "saved-visitor";
    }
<<<<<<< HEAD
    @GetMapping("/update-visitor/{id}")
    public ModelAndView updateVisitor(@PathVariable(value = "id", required = false) Integer visitorId) {
=======
    @GetMapping("/update-visitor")
    public ModelAndView updateVisitorView(@RequestParam Integer visitor_id) {
>>>>>>> parent of 468bafe (Fix: Update functionality)
        ModelAndView mav = new ModelAndView("new-visitor");
        Optional<VisitorEntity> visitor = visitorService.findVisitor(visitor_id);
        mav.addObject("visitor", visitor);
        return mav;
    }
    @GetMapping("/delete-visitor/{id}")
    public String deleteVisitor(@PathVariable(value = "id", required = false) Integer visitorId) {
        System.out.println(visitorId);
        visitorService.deleteVisitor(visitorId);
        return "redirect:/list-visitor";
    }
}
