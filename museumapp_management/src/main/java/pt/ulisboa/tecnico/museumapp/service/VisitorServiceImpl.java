package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;
import pt.ulisboa.tecnico.museumapp.repositories.VisitRepository;
import pt.ulisboa.tecnico.museumapp.repositories.VisitorRepository;

import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    VisitRepository visitRepository;

    @Override
    public Iterable<VisitorEntity> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public VisitorEntity createVisitor(VisitorEntity visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public Optional<VisitorEntity> findVisitor(Integer visitorId) {return visitorRepository.findById(visitorId);}

    @Override
    public void deleteVisitor(Integer id){
        visitorRepository.deleteById(id);
    }

    @Override
    public Iterable<VisitEntity> getAllVisits() {
        return visitRepository.findAll();
    }
}
