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
}
