package pt.ulisboa.tecnico.museumapp_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp_backend.entities.Visitor;
import pt.ulisboa.tecnico.museumapp_backend.exception.EntityNotFoundException;
import pt.ulisboa.tecnico.museumapp_backend.repository.VisitorRepository;

import java.util.List;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    public List<Visitor> findAll(){
        return visitorRepository.findAll();
    }

    public Visitor findById(long id){
        return visitorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Visitor save(Visitor visitor){
        return visitorRepository.save(visitor);
    }

    public void deleteById(long id){
        visitorRepository.deleteById(id);
    }

}
