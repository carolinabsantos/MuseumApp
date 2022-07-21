package pt.ulisboa.tecnico.museumapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ulisboa.tecnico.museumapp.entities.TimeSlotEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TimeSlotRepository extends JpaRepository <TimeSlotEntity, Integer> {
    List<TimeSlotEntity> findAll();

}

