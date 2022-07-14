package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;

import java.util.Optional;

public interface VisitService {
    Iterable <VisitEntity> getAllVisits();
    VisitEntity createVisit(VisitEntity visit);

    Optional<VisitEntity> findVisit(Integer visitId);

    Optional<VisitEntity> findById(Integer visitId);

    void deleteVisit(Integer visitId);

}
