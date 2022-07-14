package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.repositories.TimeMachineRepository;
import pt.ulisboa.tecnico.museumapp.repositories.VisitRepository;

import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService{
    @Autowired
    VisitRepository visitRepository;

    @Autowired
    TimeMachineRepository timeMachineRepository;

    @Override
    public Iterable<VisitEntity> getAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    public VisitEntity createVisit(VisitEntity visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Optional<VisitEntity> findVisit(Integer visitId) {
        return visitRepository.findById(visitId);
    }

    @Override
    public Optional<VisitEntity> findById(Integer visitId){
        return visitRepository.findById(visitId);
    }

    @Override
    public void deleteVisit(Integer visitId){
        visitRepository.deleteById(visitId);
    }
}
