package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.repositories.ArtifactRepository;
import pt.ulisboa.tecnico.museumapp.repositories.TimeMachineRepository;

import javax.persistence.PreRemove;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Service
public class ArtifactServiceImpl implements ArtifactService{

    @Autowired
    ArtifactRepository artifactRepository;
    @Autowired
    TimeMachineRepository timeMachineRepository;

    @Autowired
    TimeMachineService timeMachineService;


    @Override
    public Iterable<ArtifactEntity> getAllArtifacts() {
        return artifactRepository.findAll();
    }

    @Override
    public HashMap<String, HashMap<String, String>> listToDictionary(List<ArtifactEntity> artifacts) {
        HashMap<String, String> dictionary = new HashMap<>();
        HashMap<String, HashMap<String, String>> dictionaryFinal=new HashMap<>();
        for (int i = 0; i < artifacts.size(); i++) {
            for (ArtifactEntity a : artifacts) {
                String id = a.getId().toString();
                dictionaryFinal.put(id, new HashMap<>());
                dictionary = artifactToDictionary(a, dictionary);
                dictionaryFinal.put(a.getId().toString(), dictionary);
            }
        }
        return dictionaryFinal;
    }

    @Override
    public HashMap<String, String> artifactToDictionary(ArtifactEntity a, HashMap<String, String> dictionary) {
        dictionary.put("id", a.getId().toString());
        dictionary.put("name", a.getName());
        dictionary.put("designation", a.getDesignation());
        dictionary.put("description", a.getDescription());
        dictionary.put("techCharacteristics", a.getTechCharacteristics());
        dictionary.put("moreInfo", a.getMoreInfo());
        dictionary.put("model", a.getModel());
        dictionary.put("country", a.getCountry());
        dictionary.put("brand", a.getBrand());
        dictionary.put("madeIn", a.getMadeIn());
        dictionary.put("creationYear", a.getCreationYear().toString());
        dictionary.put("serialNumber", a.getSerialNumber());
        dictionary.put("donatedBy", a.getDonatedBy());
        dictionary.put("exhibitor", a.getExhibitor());
        dictionary.put("itemNo", a.getItemNo().toString());
        dictionary.put("typeCat", a.getTypeCat());
        dictionary.put("category", a.getCategory());
        dictionary.put("category2", a.getCategory2());
        dictionary.put("category3", a.getCategory3());
        dictionary.put("category4", a.getCategory4());
        dictionary.put("timeToVisit", a.getTimeToVisit().toString());
        return dictionary;
    }
    @Override
    public HashMap<String, HashMap<String, String>> getAllArtifactsForExhibitor(String exhibitor_name){
        List<ArtifactEntity> temp = artifactRepository.findAll();
        List<ArtifactEntity> artifacts = new ArrayList<>();
        for(ArtifactEntity a :temp){
            if(a.getExhibitor().equals(exhibitor_name)){
                artifacts.add(a);
            }
        }
        HashMap<String, HashMap<String, String>> dictionaryFinal= listToDictionary(artifacts);
        System.out.println("Dictionary Final Final: " + dictionaryFinal);
        return dictionaryFinal;
    }

    @Override
    public HashMap<String, HashMap<String, String>> getAllArtifactsForExhibitorAndTimeMachine(String exhibitor_name, String timeMachine_name){
        List<ArtifactEntity> temp = artifactRepository.findAll();
        List<ArtifactEntity> artifacts = new ArrayList<>();
        for (ArtifactEntity a:temp){
            if(a.getExhibitor().equals(exhibitor_name)){
                List<TimeMachineEntity> tms = a.getTimeMachines();
                for(TimeMachineEntity tm : tms){
                    if (tm.getName().equals(timeMachine_name)) {
                        artifacts.add(a);
                    }
                }
            }
        }
        HashMap<String, HashMap<String, String>> dictionaryFinal = listToDictionary(artifacts);
        System.out.println("Dictionary Final Final: " + dictionaryFinal);
        return dictionaryFinal;
    }

    @Override
    public HashMap<String, String> getArtifactForExhibitor(Integer artifactId){
        HashMap<String, String> dictionary = new HashMap<>();
        ArtifactEntity a = artifactRepository.getById(artifactId);
        dictionary = artifactToDictionary(a, dictionary);
        return dictionary;
    }

    @Override
    public void SendToExhibitor(HashMap<String, String> dict, HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        DataInputStream in = new DataInputStream(request.getInputStream());
        String message;
        try {
            message = "100 ok";
        } catch (Throwable t) {
            message = "200 " + t.toString();
        }

        System.out.println(message);
        JSONObject json = new JSONObject(dict);
        PrintWriter out = response.getWriter();
        out.write(json.toString());
        in.close();
        out.close();
        out.flush();
    }

    @Override
    public void updateTimeMachine(ArtifactEntity artifact) {
        List<TimeMachineEntity> timeMachines = timeMachineRepository.findAll();
        for (TimeMachineEntity t : timeMachines) {
            if (timeMachineService.artifactOnTimeMachineByCategory(artifact,t) | timeMachineService.artifactOnTimeMachineByYear(artifact,t) | timeMachineService.artifactOnTimeMachineByTopic(artifact,t)) {
                if(!(t.getArtifacts().contains(artifact))){
                    t.addArtifact(artifact);
                }
            }
        }
    }

    @Override
    public List<ArtifactEntity> getArtifactsFromTimeMachine(TimeMachineEntity t) {
        List<ArtifactEntity> artifactsFromTimeMachine = new ArrayList<>();
        for(ArtifactEntity a : artifactRepository.findAll()){
            if (a.getCategory().equals(t.getName()) | a.getCategory2().equals(t.getName()) | a.getCategory3().equals(t.getName()) | a.getCategory4().equals(t.getName())){
                artifactsFromTimeMachine.add(a);
            }
        }
        System.out.println("artifactsFromTimeMachine");
        System.out.println(artifactsFromTimeMachine);
        return artifactsFromTimeMachine;
    }
    @Override
    public ArtifactEntity createArtifact(ArtifactEntity artifact) {
        updateTimeMachine(artifact);
        return artifactRepository.save(artifact);
    }
    @Override
    public Optional<ArtifactEntity> findArtifact(Integer artifactId) {return artifactRepository.findById(artifactId);}

    @Override
    @PreRemove
    public void removeArtifactFromTimeMachines(ArtifactEntity artifact) {
        for (TimeMachineEntity tm : timeMachineRepository.findAll()) {
            List<ArtifactEntity> artifacts = tm.getArtifacts();
            artifacts.remove(artifact);
            tm.setArtifacts(artifacts);
        }
    }
    @Override
    public void deleteArtifact(Integer id){
        artifactRepository.deleteById(id);
    }
}
