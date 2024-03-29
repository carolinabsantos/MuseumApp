package pt.ulisboa.tecnico.museumapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ulisboa.tecnico.museumapp.entities.VisitorEntity;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VisitorRepository  extends JpaRepository<VisitorEntity, Integer> {
}
