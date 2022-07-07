package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;
import pt.ulisboa.tecnico.museumapp.repositories.ArtifactRepository;
import pt.ulisboa.tecnico.museumapp.repositories.TimeMachineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArtifactServiceImpl implements ArtifactService{

    @Autowired
    ArtifactRepository artifactRepository;
    @Autowired
    TimeMachineRepository timeMachineRepository;

    @Override
    public Iterable<ArtifactEntity> getAllArtifacts() {
        return artifactRepository.findAll();
    }

    public void updateTimeMachine(ArtifactEntity artifact) {
        List<TimeMachineEntity> timeMachines = timeMachineRepository.findAll();
        for (TimeMachineEntity t : timeMachines) {
            if (artifact.getCategory().equals(t.getName()) | artifact.getCategory2().equals(t.getName()) |artifact.getCategory3().equals(t.getName()) | artifact.getCategory4().equals(t.getName())){
                t.addArtifact(artifact);
            }
            if (artifact.getCreationYear().equals(t.getName())){ //YEAR
                t.addArtifact(artifact);
            }
            if (artifact.getBrand().equals(t.getName())){ //TOPIC - Brand
                t.addArtifact(artifact);
            }
            if (artifact.getCountry().equals(t.getName())){ //TOPIC - Country
                t.addArtifact(artifact);
            }
        }
    }
    @Override
    public ArtifactEntity createArtifact(ArtifactEntity artifact) {
        updateTimeMachine(artifact);
        return artifactRepository.save(artifact);
    }
    @Override
    public Optional<ArtifactEntity> findArtifact(Integer artifactId) {return artifactRepository.findById(artifactId);}

    @Override
    public void deleteArtifact(Integer id){
        artifactRepository.deleteById(id);
    }
}
