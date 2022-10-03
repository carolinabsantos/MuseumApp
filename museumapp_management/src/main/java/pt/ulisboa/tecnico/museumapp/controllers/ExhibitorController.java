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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.thymeleaf.util.ListUtils.size;

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

        System.out.println(message + " Got to getAllArtifacts");
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

        System.out.println(message + " Got to getArtifact");
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

        System.out.println(message + " Got to getTimeMachineExhibitorArtifacts");
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

        System.out.println(message + " Got to visitState");
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

    @GetMapping ("/endExhibitor") public void endExhibitor(@RequestParam("visit_id") String visit_id, HttpServletRequest request,
                                                          HttpServletResponse response) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String endTime = dtf.format(now);
        List<String> exhibitors;

        VisitEntity visit = visitService.findVisit(Integer.valueOf(visit_id)).get();
        System.out.println("Visit to change: " + visit);
        System.out.println("Counter to change: " + visit.getExhibitors_counter());
        Integer counter = visit.getExhibitors_counter() + 1;
        System.out.println("Counter changed: " + counter);
        visitService.updateVisitExhibitorCounter(Integer.valueOf(visit_id), counter);
        exhibitors = List.of((visit.getExhibitors().split("-")));
        System.out.println("Visit exhibitors list: " + exhibitors);
        if (counter == exhibitors.size()){
            visitService.endVisit(Integer.valueOf(visit_id), endTime);
        }
        System.out.println("Visit changed: " + visitService.findVisit(Integer.valueOf(visit_id)).get());
        HashMap<String, String> visitInfo = visitService.listToDictionary(visit);
        System.out.println(visitInfo);

        DataInputStream in = new DataInputStream(request.getInputStream());
        String message;
        try {
            message = "100 ok";
        } catch (Throwable t) {
            message = "200 " + t.toString();
        }

        System.out.println(message + " Got to endOfExhibitor");
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
