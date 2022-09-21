package pt.ulisboa.tecnico.museumapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.models.TimeMachine;
import pt.ulisboa.tecnico.museumapp.service.ArtifactService;
import pt.ulisboa.tecnico.museumapp.service.TimeMachineService;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller // This means that this class is a Controller
public class TimeMachineController implements WebMvcConfigurer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String ROOM_PLAN_IMAGE_PATH = "./src/main/resources/static/room-plans/";
    @Autowired
    private TimeMachineService timeMachineService;
    @Autowired
    private ArtifactService artifactService;

    @GetMapping("/new-time-machine")
    public String createTimeMachineForm(Model model) {
        model.addAttribute("timeMachine", new TimeMachine());
        return "new-time-machine";
    }

    @GetMapping("/list-time-machine")
    public ModelAndView findAllTimeMachines() {
        ModelAndView mav = new ModelAndView("list-time-machine");
        timeMachineService.updateArtifacts();
        timeMachineService.updateVisitTime();
        mav.addObject("timeMachines", timeMachineService.getAllTimeMachines());
        return mav;
    }
    @GetMapping("/list-artifact-tm/{id}")
    public ModelAndView findAllArtifactsFromTimeMachine(@PathVariable(value = "id", required = false) Integer timeMachineId) {
        ModelAndView mav = new ModelAndView("list-artifact-tm");
        mav.addObject("artifacts", timeMachineService.listArtifacts(timeMachineId));
        return mav;
    }

    @PostMapping("/save-time-machine")
    public String saveTimeMachine(@ModelAttribute TimeMachine timeMachine, HttpServletRequest request, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String roomPlanName = timeMachineService.getRoomPlanName(timeMachine.getName());
        String uploadDir = ROOM_PLAN_IMAGE_PATH;


        String uploadDirectory = request.getServletContext().getRealPath(uploadDir);
        log.info("uploadDirectory:: " + uploadDirectory);
        String fileName = "TM-" + timeMachineService.getRoomPlanName(timeMachine.getName());
        String filePath = Paths.get(uploadDirectory, fileName).toString();
        log.info("FilePath: " + filePath);
        log.info("FileName: " + fileName);

        try {
            // Save the file locally
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(multipartFile.getBytes());
            stream.close();
        } catch (Exception e) {
            log.info("in catch");
            e.printStackTrace();
        }

        TimeMachineEntity timeMachineFinal = new TimeMachineEntity(timeMachine.getType(), timeMachine.getName(), timeMachine.getCapacity(), roomPlanName);
        timeMachineService.createTimeMachine(timeMachineFinal);
        TimeMachineEntity tm = timeMachineService.findTimeMachineByName(timeMachineFinal.getName()).get();

        timeMachineService.saveRoomPlan(uploadDir, roomPlanName, multipartFile);
        artifactService.getArtifactsFromTimeMachine(tm);
        return "saved-time-machine";
    }

    @GetMapping("/update-time-machine")
    public ModelAndView updateVisitorView(@RequestParam Integer timeMachine_id) {
        ModelAndView mav = new ModelAndView("new-time-machine");
        TimeMachineEntity timeMachine = timeMachineService.findTimeMachine(timeMachine_id).get();
        mav.addObject("timeMachine", timeMachine);
        timeMachineService.deleteTimeMachine(timeMachine_id);
        return mav;
    }

    @GetMapping("/show-room-plan")
    public String processUrl(Model model, @RequestParam Integer timeMachine_id) throws IOException {
        String roomPlanName = timeMachineService.findById(timeMachine_id).get().getImage();
        String []formatSplit=roomPlanName.split("\\.",2);
        String format = formatSplit[1];

        BufferedImage bufferedImage= ImageIO.read(new File(ROOM_PLAN_IMAGE_PATH + roomPlanName));
        String imagePath= timeMachineService.encodeToString(bufferedImage,format);
        model.addAttribute("path", imagePath);

        return "show-room-plan";
    }

    @GetMapping("/delete-time-machine/{id}")
    public String deleteTimeMachineT(@PathVariable(value = "id", required = false) Integer timeMachineId) {
        timeMachineService.deleteRoomPlan(timeMachineId);
        timeMachineService.deleteTimeMachine(timeMachineId);
        return "redirect:/list-time-machine";
    }
}
