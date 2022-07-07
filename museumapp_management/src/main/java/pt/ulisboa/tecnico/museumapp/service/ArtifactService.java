package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;

import java.util.Optional;

public interface ArtifactService {
    Iterable <ArtifactEntity> getAllArtifacts();
    ArtifactEntity createArtifact(ArtifactEntity artifact);

    Optional<ArtifactEntity> findArtifact(Integer artifactId);
    void deleteArtifact(Integer id);
}
