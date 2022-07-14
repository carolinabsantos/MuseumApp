package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
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
    @Override
    public void updateTimeMachine(ArtifactEntity artifact) {
        List<TimeMachineEntity> timeMachines = timeMachineRepository.findAll();
        for (TimeMachineEntity t : timeMachines) {
            if (artifact.getCategory().equals(t.getName()) | artifact.getCategory2().equals(t.getName()) |artifact.getCategory3().equals(t.getName()) | artifact.getCategory4().equals(t.getName())){
                t.addArtifact(artifact);
            }
        }
    }

    @Override
    public void updateTimeMachine() {
        for(ArtifactEntity a : artifactRepository.findAll()){
            for (TimeMachineEntity t : timeMachineRepository.findAll()) {
                if (a.getCategory().equals(t.getName()) | a.getCategory2().equals(t.getName()) |a.getCategory3().equals(t.getName()) | a.getCategory4().equals(t.getName())){
                    t.addArtifact(a);
                }
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
