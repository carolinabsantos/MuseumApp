package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;

import java.util.Optional;

public interface TimeMachineService {
    Iterable <TimeMachineEntity> getAllTimeMachines();
    TimeMachineEntity createTimeMachine(TimeMachineEntity timeMachine);

    Optional<TimeMachineEntity> findTimeMachine(Integer timeMachineId);
}
