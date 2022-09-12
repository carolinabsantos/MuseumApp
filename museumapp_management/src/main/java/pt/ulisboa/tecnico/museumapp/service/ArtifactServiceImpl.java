package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.repositories.ArtifactRepository;
import pt.ulisboa.tecnico.museumapp.repositories.TimeMachineRepository;

import javax.persistence.PreRemove;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
