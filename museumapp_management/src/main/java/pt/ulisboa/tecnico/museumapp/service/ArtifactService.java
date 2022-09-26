package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;

import javax.persistence.PreRemove;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ArtifactService {
    Iterable <ArtifactEntity> getAllArtifacts();

    HashMap<String, HashMap<String, String>> getAllArtifactsForExhibitor(String exhibitor_name);

    HashMap<String, String> listToDictionary(ArtifactEntity a, HashMap<String, String> dictionary);

    HashMap<String, String> getAllArtifactsForExhibitorAndTimeMachine(String exhibitor_name, String timeMachine_name);

    HashMap<String, String> getArtifactForExhibitor(Integer artifactId);

    void SendToExhibitor(HashMap<String, String> dict, HttpServletRequest request,
                         HttpServletResponse response) throws IOException;

    void updateTimeMachine(ArtifactEntity artifact);

    List<ArtifactEntity> getArtifactsFromTimeMachine(TimeMachineEntity t);

    ArtifactEntity createArtifact(ArtifactEntity artifact);

    Optional<ArtifactEntity> findArtifact(Integer artifactId);

    @PreRemove
    void removeArtifactFromTimeMachines(ArtifactEntity artifact);

    void deleteArtifact(Integer id);
}
