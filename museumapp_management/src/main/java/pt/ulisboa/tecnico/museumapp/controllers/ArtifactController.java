package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.models.Artifact;
import pt.ulisboa.tecnico.museumapp.service.ArtifactService;

import java.util.Optional;

@Controller // This means that this class is a Controller
public class ArtifactController implements WebMvcConfigurer{
    @Autowired
    private ArtifactService artifactService;

    @GetMapping("/new-artifact")
    public String createArtifactForm(Model model) {

        model.addAttribute("artifact", new Artifact());
        return "new-artifact";
    }

    @GetMapping("/list-artifact")
    public ModelAndView findAllVisitors() {
        ModelAndView mav = new ModelAndView("list-artifact");
        mav.addObject("artifacts", artifactService.getAllArtifacts());
        return mav;
    }

    @PostMapping("/save-artifact")
    public String saveArtifact(@ModelAttribute Artifact artifact) {
        artifact = artifact.getNullValues(artifact);
        ArtifactEntity artifactFinal = new ArtifactEntity(artifact.getName(),artifact.getDesignation(),artifact.getDescription(),artifact.getTechCharacteristics(),artifact.getMoreInfo(), artifact.getModel(), artifact.getCountry(),artifact.getBrand(),artifact.getMadeIn(),artifact.getCreationYear(),artifact.getSerialNumber(),artifact.getDonatedBy(),artifact.getExhibitor(),artifact.getItemNo(),artifact.getTypeCat(),artifact.getCategory(),artifact.getCategory2(),artifact.getCategory3(),artifact.getCategory4(), artifact.getTimeToVisit());
        artifactService.createArtifact(artifactFinal);
        return "saved-artifact";
    }

    @GetMapping("/update-artifact")
    public ModelAndView updateVisitorView(@RequestParam Integer artifact_id) {
        ModelAndView mav = new ModelAndView("new-artifact");
        ArtifactEntity artifact = artifactService.findArtifact(artifact_id).get();
        mav.addObject("artifact", artifact);
        artifactService.removeArtifactFromTimeMachines(artifact);
        artifactService.deleteArtifact(artifact_id);
        return mav;
    }

    @GetMapping("/delete-artifact/{id}")
    public String deleteArtifact(@PathVariable(value = "id", required = false) Integer artifactId) {
        artifactService.deleteArtifact(artifactId);
        return "redirect:/list-artifact";
    }
}
