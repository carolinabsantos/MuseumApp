package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.models.Visitor;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;
import pt.ulisboa.tecnico.museumapp.repositories.VisitorRepository;
import pt.ulisboa.tecnico.museumapp.service.VisitorService;

import java.util.Optional;

@Controller // This means that this class is a Controller
public class VisitorController implements WebMvcConfigurer{
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private VisitorRepository visitorRepository;

    @GetMapping("/list-visitor")
    public ModelAndView findAllVisitors() {
        ModelAndView mav = new ModelAndView("list-visitor");
        mav.addObject("visitors", visitorService.getAllVisitors());
        return mav;
    }
    @GetMapping("/new-visitor")
    public String createVisitorForm(Model model) {
        model.addAttribute("visitor", new Visitor());
        return "new-visitor";
    }
    @PostMapping("/save-visitor")
    public String saveVisitor(@ModelAttribute Visitor visitor) {
        VisitorEntity visitorFinal = new VisitorEntity(visitor.getfName(), visitor.getlName(), visitor.getEmail_address(), visitor.getContact(), visitor.getNoVisitors());
        visitorService.createVisitor(visitorFinal);
        return "saved-visitor";
    }
    @GetMapping("/update-visitor/{id}")
    public ModelAndView updateVisitor(@PathVariable(value = "id", required = false) Integer visitorId, Model model) {
        ModelAndView mav = new ModelAndView("new-visitor");
        VisitorEntity visitor = visitorService.findVisitor(visitorId).get();
        mav.addObject("visitor", visitor);
        visitorService.deleteVisitor(visitorId);
        return mav;
    }
    @GetMapping("/delete-visitor/{id}")
    public String deleteVisitor(@PathVariable(value = "id", required = false) Integer visitorId) {
        System.out.println(visitorId);
        visitorService.deleteVisitor(visitorId);
        return "redirect:/list-visitor";
    }



}
