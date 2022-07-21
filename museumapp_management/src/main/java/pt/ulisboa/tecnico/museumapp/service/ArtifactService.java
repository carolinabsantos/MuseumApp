package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;

import javax.persistence.PreRemove;
import java.util.List;
import java.util.Optional;

public interface ArtifactService {
    Iterable <ArtifactEntity> getAllArtifacts();

    void updateTimeMachine(ArtifactEntity artifact);

    List<ArtifactEntity> getArtifactsFromTimeMachine(TimeMachineEntity t);

    ArtifactEntity createArtifact(ArtifactEntity artifact);

    Optional<ArtifactEntity> findArtifact(Integer artifactId);

    @PreRemove
    void removeArtifactFromTimeMachines(ArtifactEntity artifact);

    void deleteArtifact(Integer id);
}
