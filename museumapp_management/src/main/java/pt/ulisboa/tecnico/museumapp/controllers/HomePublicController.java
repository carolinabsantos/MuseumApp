package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class HomePublicController implements WebMvcConfigurer {

    @GetMapping("/museum") public String homePublic() {
        return "public/index-public";
    }
    @GetMapping("/visit") public String indexBookVisit() {
        return "index-visit";
    }
    @GetMapping("/available-visits") public String indexAvailableVisits() {
        return "available-visits";
    }
}
