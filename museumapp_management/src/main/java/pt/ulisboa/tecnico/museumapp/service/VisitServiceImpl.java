package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp.entities.State;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.models.TimeMachine;
import pt.ulisboa.tecnico.museumapp.models.Visit;
import pt.ulisboa.tecnico.museumapp.repositories.TimeMachineRepository;
import pt.ulisboa.tecnico.museumapp.repositories.VisitRepository;

import java.util.Iterator;
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
    public void deleteVisit(VisitEntity visit){
        visitRepository.delete(visit);
    }

    @Override
    public Visit createVisitFromId(Integer visit_id){
        VisitEntity v = findVisit(visit_id).get();
        TimeMachineEntity t = timeMachineRepository.findByNameEquals(v.getTimeMachine().getName()).get();
        TimeMachine timeMachine = new TimeMachine(t.getType(), t.getName(), t.getCapacity());
        Visit visit = new Visit(v.getId(), timeMachine, v.getStartTime(), v.getEndTime(), v.getState(), v.getVisitor().getId(), v.getObservations(), v.getVisitDate());
        return visit;
    }

    @Override
    public VisitEntity findVisitByVisitorId(Integer visitorId){
        for (VisitEntity v : getAllVisits()){
            if(v.getVisitor().getId() == visitorId){
                return v;
            }
        }
        return null;
    }
    @Override
    public VisitEntity getLastElement() {
        Iterable<VisitEntity> visits = getAllVisits();
        final Iterator itr = visits.iterator();
        VisitEntity lastElement = (VisitEntity) itr.next();
        while(itr.hasNext()) {
            lastElement = (VisitEntity) itr.next();
        }
        return lastElement;
    }
    @Override
    public void startVisit(Integer visitId, String start_time) {
        visitRepository.updateState(visitId, State.ONGOING);
        visitRepository.updateStartTime(visitId, start_time );
        visitRepository.updateEndTime(visitId, "not_ended" );
    }
    
    @Override
    public void endVisit(Integer visitId, String end_time) {
        visitRepository.updateState(visitId, State.ENDED);
        visitRepository.updateEndTime(visitId, end_time );
    }
    @Override
    public void updateVisitObservations(Integer visitId, String observations) {
        visitRepository.updateObservations(visitId, observations);
    }

}
