package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.State;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.models.Visit;

import java.util.HashMap;
import java.util.Optional;

public interface VisitService {
    Iterable <VisitEntity> getAllVisits();
    VisitEntity createVisit(VisitEntity visit);

    Optional<VisitEntity> findVisit(Integer visitId);

    Optional<VisitEntity> findById(Integer visitId);

    void deleteVisit(VisitEntity visit);

    Visit createVisitFromId(Integer visit_id);

    VisitEntity findVisitByVisitorId(Integer visitorId);

    VisitEntity getLastElement();

    void startVisit(Integer visitId, String start_time);

    void endVisit(Integer visitId, String end_time);

    void updateVisitObservations(Integer visitId, String observations);

    HashMap<String, String> listToDictionary(VisitEntity v);
}
