package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.service.ArtifactService;
import pt.ulisboa.tecnico.museumapp.service.VisitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Controller
public class ExhibitorController implements WebMvcConfigurer {
    @Autowired
    private ArtifactService artifactService;
    @Autowired
    private VisitService visitService;

    @GetMapping("/getAllArtifacts") public void getAllArtifactsForExhibitor(@RequestParam("exhibitor_name") String exhibitor_name, HttpServletRequest request,
                                                                HttpServletResponse response) throws IOException {

        HashMap<String, HashMap<String, String>> artifacts = artifactService.getAllArtifactsForExhibitor(exhibitor_name);

        DataInputStream in = new DataInputStream(request.getInputStream());
        String message;
        try {
            message = "100 ok";
        } catch (Throwable t) {
            message = "200 " + t.toString();
        }

        System.out.println(message);
        JSONObject json = new JSONObject(artifacts);
        PrintWriter out = response.getWriter();
        out.write(json.toString());
        in.close();
        out.close();
        out.flush();
    }


    @GetMapping("/getArtifact") public void getArtifact(@RequestParam("artifact_id") Integer artifact_id, HttpServletRequest request,
                                                                HttpServletResponse response) throws IOException {
        HashMap<String, String> artifact = artifactService.getArtifactForExhibitor(artifact_id);
        DataInputStream in = new DataInputStream(request.getInputStream());
        String message;
        try {
            message = "100 ok";
        } catch (Throwable t) {
            message = "200 " + t.toString();
        }

        System.out.println(message);
        JSONObject json = new JSONObject(artifact);
        PrintWriter out = response.getWriter();
        out.write(json.toString());
        in.close();
        out.close();
        out.flush();
    }

    @GetMapping("/timeMachineExhibitorArtifacts") public void getTimeMachineExhibitorArtifacts(@RequestParam("exhibitor_name") String exhibitor_name, @RequestParam("timeMachine_name") String timeMachine_name, HttpServletRequest request,
                                                        HttpServletResponse response) throws IOException {
        HashMap<String, HashMap<String, String>> artifacts = artifactService.getAllArtifactsForExhibitorAndTimeMachine(exhibitor_name, timeMachine_name);

        DataInputStream in = new DataInputStream(request.getInputStream());
        String message;
        try {
            message = "100 ok";
        } catch (Throwable t) {
            message = "200 " + t.toString();
        }

        System.out.println(message);
        JSONObject json = new JSONObject(artifacts);
        PrintWriter out = response.getWriter();
        out.write(json.toString());
        in.close();
        //System.out.println("1");
        out.close();
        //System.out.println("2");
        out.flush();
        //System.out.println("3");
    }


    @GetMapping ("/visitState") public void getVisitState(@RequestParam("visit_id") String visit_id, HttpServletRequest request,
                                                                  HttpServletResponse response) throws IOException {
        VisitEntity visit = visitService.findVisit(Integer.valueOf(visit_id)).get();
        HashMap<String, String> visitInfo = visitService.listToDictionary(visit);
        System.out.println(visitInfo);

        DataInputStream in = new DataInputStream(request.getInputStream());
        String message;
        try {
            message = "100 ok";
        } catch (Throwable t) {
            message = "200 " + t.toString();
        }

        System.out.println(message);
        JSONObject json = new JSONObject(visitInfo);
        PrintWriter out = response.getWriter();
        out.write(json.toString());
        in.close();
        //System.out.println("1");
        out.close();
        //System.out.println("2");
        out.flush();
        //System.out.println("3");
    }
}
