package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pt.ulisboa.tecnico.museumapp.entities.StateEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.repositories.VisitRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller // This means that this class is a Controller
public class VisitController {
    @Autowired
    VisitRepository visitRepository;

    @RequestMapping("/visit/new")
    public ModelAndView getRegistrationPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register.jsp");
        return mv;
    }

    @PostMapping("/visit/save-visit")
    public void handle(VisitEntity visit) {
        System.out.println(visit);
    }


    @PostMapping(path="/visit/new") // Map ONLY POST Requests
    public @ResponseBody String newVisit (@RequestParam TimeMachineEntity timeMachine) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        // startTime is when the visit starts and state is TO_START
        visitRepository.save(new VisitEntity(timeMachine));
        return "Visit added";
    }

    @PostMapping(path="/visit/update") // Map ONLY POST Requests
    public @ResponseBody String updateVisit (@RequestParam int visit_id
            , @RequestParam TimeMachineEntity timeMachine) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        VisitEntity visit = visitRepository.getById(visit_id);
        visit.setTimeMachine(timeMachine);
        visitRepository.save(visit);
        return "Visit updated";
    }

    @PostMapping(path = "/visit/delete")
    public @ResponseBody String deleteVisit(@RequestParam int visit_id) {
        visitRepository.delete(visitRepository.getById(visit_id));
        return "Visit deleted";
    }

    @GetMapping(path="/visit/all")
    public @ResponseBody Iterable<VisitEntity> listVisits() {
        // This returns a JSON or XML with the users
        return visitRepository.findAll();
    }
}
