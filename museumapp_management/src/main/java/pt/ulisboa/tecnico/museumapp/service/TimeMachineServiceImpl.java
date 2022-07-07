package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.repositories.TimeMachineRepository;

import java.util.Optional;

@Service
public class TimeMachineServiceImpl implements TimeMachineService{
    @Autowired
    TimeMachineRepository timeMachineRepository;

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
}
