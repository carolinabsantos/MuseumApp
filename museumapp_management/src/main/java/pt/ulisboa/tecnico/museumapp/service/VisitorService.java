package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;

import java.util.Optional;


public interface VisitorService {
    Iterable <VisitorEntity> getAllVisitors();
    VisitorEntity createVisitor(VisitorEntity visitor);
    Optional<VisitorEntity> findVisitor(Integer visitorId);
    Optional<VisitorEntity> deleteVisitor(Integer visitorId);
}
