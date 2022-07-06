package pt.ulisboa.tecnico.museumapp_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ulisboa.tecnico.museumapp_backend.entities.Visitor;
import pt.ulisboa.tecnico.museumapp_backend.repository.VisitorRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class VisitorController {

    @Autowired
    private VisitorRepository visitorRepository;

    @RequestMapping(value = "/visitor", method = RequestMethod.GET)
    public List<Visitor> Get() {
        return visitorRepository.findAll();
    }

    @RequestMapping(value = "/visitor/{id}", method = RequestMethod.GET)
    public ResponseEntity<Visitor> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Visitor> visitor = visitorRepository.findById(id);
        return visitor.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/visitor", method =  RequestMethod.POST)
    public Visitor Post(@Valid @RequestBody Visitor visitor){return visitorRepository.save(visitor);}

    @RequestMapping(value = "/visitor/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Visitor> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Visitor newVisitor)
    {
        Optional<Visitor> oldVisitor = visitorRepository.findById(id);
        if(oldVisitor.isPresent()){
            Visitor visitor = oldVisitor.get();
            visitor.setFirstName(newVisitor.getFirstName());
            visitor.setLastName(newVisitor.getLastName());
            visitor.setEmailAddress(newVisitor.getEmailAddress());
            visitor.setContact(newVisitor.getContact());
            visitor.setN_visitors(newVisitor.getN_visitors());
            visitorRepository.save(visitor);
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/visitor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Visitor> visitor = visitorRepository.findById(id);
        if(visitor.isPresent()){
            visitorRepository.delete(visitor.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}