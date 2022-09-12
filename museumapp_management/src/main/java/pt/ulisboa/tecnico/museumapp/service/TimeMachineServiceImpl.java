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
public class TimeMachineServiceImpl implements TimeMachineService{
    @Autowired
    TimeMachineRepository timeMachineRepository;

    @Autowired
    ArtifactRepository artifactRepository;

    @Override
    public Iterable<TimeMachineEntity> getAllTimeMachines() {
        return timeMachineRepository.findAll();
    }

    @Override
    public TimeMachineEntity createTimeMachine(TimeMachineEntity timeMachine) {
        return timeMachineRepository.save(timeMachine);
    }

    @Override
    public Optional<TimeMachineEntity> findTimeMachine(Integer timeMachineId) {
        return timeMachineRepository.findById(timeMachineId);
    }

    @Override
    public Optional<TimeMachineEntity> findById(Integer timeMachineId){
        return timeMachineRepository.findById(timeMachineId);
    }

    @Override
    public Optional<TimeMachineEntity> findTimeMachineByName(String name) {
        return timeMachineRepository.findByNameEquals(name);
    }

    @Override
    public void updateArtifacts() {
        for (TimeMachineEntity timeMachine : timeMachineRepository.findAll()) {
            updateArtifactsOnTimeMachine(timeMachine);
            timeMachine.setArtifactsCount(timeMachine.getArtifacts().size());
        }
    }

    @Override
    public List<ArtifactEntity> listArtifacts(Integer timeMachineId) {
        TimeMachineEntity timeMachine = timeMachineRepository.findById(timeMachineId).get();
        return timeMachine.getArtifacts();
    }

    @Override
    public void deleteTimeMachine(Integer id){
        timeMachineRepository.deleteById(id);
    }

    @Override
    public void updateVisitTime() {
        for (TimeMachineEntity timeMachine : timeMachineRepository.findAll()) {
            Integer visitTime = 0;
            for (ArtifactEntity a : timeMachine.getArtifacts()){
                visitTime += a.getTimeToVisit();
            }
            timeMachine.setVisitTime(visitTime);
        }
    }

    @Override
    public void updateArtifactsOnTimeMachine(TimeMachineEntity timeMachine){
        System.out.println("Time Machine: " + timeMachine);
        for (ArtifactEntity artifact : artifactRepository.findAll()){
            System.out.println("Artifact name: " + artifact.getName() + "; Artifact year: " + artifact.getCreationYear());
            if (artifactOnTimeMachineByCategory(artifact,timeMachine) | artifactOnTimeMachineByYear(artifact, timeMachine) | artifactOnTimeMachineByTopic(artifact, timeMachine)) {
                if(timeMachine.getArtifactsCount()==null){
                    timeMachine.addArtifact(artifact);
                    System.out.println("Added");
                }
                else if(!(timeMachine.getArtifacts().contains(artifact))){
                    timeMachine.addArtifact(artifact);
                    System.out.println("Added");
                }
            }
            System.out.println("Not Added");
        }
    }
    @Override
    public boolean artifactOnTimeMachineByCategory(ArtifactEntity artifact, TimeMachineEntity t){
        if (artifact.getCategory().equals(t.getName()) | artifact.getCategory2().equals(t.getName()) | artifact.getCategory3().equals(t.getName()) | artifact.getCategory4().equals(t.getName()))
            return true;
        return false;
    }
    @Override
    public boolean artifactOnTimeMachineByYear(ArtifactEntity artifact, TimeMachineEntity t){
        if (artifact.getCreationYear().toString().equals(t.getName()))
            return true;
        return false;
    }
    @Override
    public boolean artifactOnTimeMachineByTopic(ArtifactEntity artifact, TimeMachineEntity t){
        if(artifact.getCountry().equals(t.getName()) | artifact.getBrand().equals(t.getName()) | artifact.getDonatedBy().equals(t.getName()) | artifact.getExhibitor().equals(t.getName()))
            return true;
        return false;
    }

}
