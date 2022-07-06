package pt.ulisboa.tecnico.museumapp_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pt.ulisboa.tecnico.museumapp_backend.entities.Visitor;

import java.util.List;
@Repository
@Transactional
public interface VisitorRepository extends JpaRepository<Visitor, Long>{ }