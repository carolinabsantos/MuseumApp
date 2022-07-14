package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;
import pt.ulisboa.tecnico.museumapp.models.Visitor;
import pt.ulisboa.tecnico.museumapp.service.VisitorService;

import java.util.Optional;


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
        return "new-visitor";
    }
    @PostMapping("/save-visitor")
    public String saveVisitor(@ModelAttribute Visitor visitor) {
        VisitorEntity visitorFinal = new VisitorEntity(visitor.getfName(), visitor.getlName(), visitor.getEmail_address(), visitor.getContact(), visitor.getNoVisitors());
        visitorService.createVisitor(visitorFinal);
        visitor.setId(visitorFinal.getId());
        return "saved-visitor";
    }
    @GetMapping("/update-visitor")
    public ModelAndView updateVisitorView(@RequestParam Integer visitor_id) {
        ModelAndView mav = new ModelAndView("new-visitor");
        Optional<VisitorEntity> visitor = visitorService.findVisitor(visitor_id);
        mav.addObject("visitor", visitor);
        return mav;
    }
    @GetMapping("/delete-visitor/{id}")
    public String deleteVisitor(@PathVariable(value = "id", required = false) Integer visitorId) {
        visitorService.deleteVisitor(visitorId);
        return "redirect:/list-visitor";
    }
}
