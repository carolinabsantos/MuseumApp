package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;

import java.util.List;
import java.util.Optional;

public interface TimeMachineService {
    Iterable <TimeMachineEntity> getAllTimeMachines();
    TimeMachineEntity createTimeMachine(TimeMachineEntity timeMachine);

    Optional<TimeMachineEntity> findTimeMachine(Integer timeMachineId);

    Optional<TimeMachineEntity> findById(Integer timeMachineId);

    Optional<TimeMachineEntity> findTimeMachineByName(String name);

    void updateArtifacts();

    List<ArtifactEntity> listArtifacts(Integer timeMachineId);

    void deleteTimeMachine(Integer timeMachineId);

    void updateVisitTime();

    void updateArtifactsOnTimeMachine(TimeMachineEntity t);

    boolean artifactOnTimeMachineByCategory(ArtifactEntity artifact, TimeMachineEntity t);

    boolean artifactOnTimeMachineByYear(ArtifactEntity artifact, TimeMachineEntity t);

    boolean artifactOnTimeMachineByTopic(ArtifactEntity artifact, TimeMachineEntity t);
}
