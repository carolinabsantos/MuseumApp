package pt.ulisboa.tecnico.museumapp_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pt.ulisboa.tecnico.museumapp_backend.entities.Visit;

@Controller
public class VisitController {
    @GetMapping("/visit")
    public String visitForm(Model model) {
        model.addAttribute("visit", new Visit());
        return "visit";
    }

    @PostMapping("/visit")
    public String visitSubmit(@ModelAttribute Visit visit, Model model) {
        model.addAttribute("visit", visit);
        return "result";
    }
}
