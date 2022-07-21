package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class Home implements WebMvcConfigurer {

    @GetMapping("/home") public String home() {
        return "index";
    }
    @GetMapping("/index-visitor")
    public String indexVisitor() {
        return "index-visitor";
    }
    @GetMapping("/index-visit")
    public String indexVisit() {
        return "index-visit";
    }
    @GetMapping("/index-artifact")
    public String indexArtifact() {
        return "index-artifact";
    }
    @GetMapping("/index-time-machine")
    public String indexTimeMachine() {
        return "index-time-machine";
    }

    @GetMapping("/index-system")
    public String indexSystem() {
        return "index-system";
    }

}
