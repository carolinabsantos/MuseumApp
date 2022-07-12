package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;
import pt.ulisboa.tecnico.museumapp.models.TimeMachine;
import pt.ulisboa.tecnico.museumapp.repositories.TimeMachineRepository;
import pt.ulisboa.tecnico.museumapp.service.TimeMachineService;

import java.util.Optional;

import static java.lang.Integer.parseInt;

@Controller // This means that this class is a Controller
public class TimeMachineController implements WebMvcConfigurer {
    @Autowired
    private TimeMachineService timeMachineService;
    @Autowired
    private TimeMachineRepository timeMachineRepository;

    @GetMapping("/new-time-machine")
    public String createTimeMachineForm(Model model) {
        model.addAttribute("timeMachine", new TimeMachine());
        return "new-time-machine";
    }

    @GetMapping("/list-time-machine")
    public ModelAndView findAllTimeMachines() {
        ModelAndView mav = new ModelAndView("list-time-machine");
        mav.addObject("timeMachines", timeMachineService.getAllTimeMachines());
        return mav;
    }

    @PostMapping("/save-time-machine")
    public String saveTimeMachine(@ModelAttribute TimeMachine timeMachine) {
        TimeMachineEntity timeMachineFinal = new TimeMachineEntity(timeMachine.getType(), timeMachine.getName());
        timeMachineService.createTimeMachine(timeMachineFinal);
        System.out.println(timeMachine);
        return "saved-time-machine";
    }

    @GetMapping("/update-time-machine")
    public ModelAndView updateVisitorView(@RequestParam Integer timeMachine_id) {
        ModelAndView mav = new ModelAndView("new-time-machine");
        Optional<TimeMachineEntity> timeMachine = timeMachineService.findTimeMachine(timeMachine_id);
        mav.addObject("timeMachine", timeMachine);
        return mav;
    }

    @GetMapping("/delete-time-machine/{id}")
    public String deleteTimeMachineT(@PathVariable(value = "id", required = false) Integer timeMachineId) {
        System.out.println(timeMachineId);
        timeMachineService.deleteTimeMachine(timeMachineId);
        return "redirect:/list-time-machine";
    }
}
