package pt.ulisboa.tecnico.museumapp_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ulisboa.tecnico.museumapp_backend.entities.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long>{}