package pt.ulisboa.tecnico.museumapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.ulisboa.tecnico.museumapp.entities.State;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface VisitRepository extends JpaRepository<VisitEntity, Integer> {
    public List<VisitEntity> findAll();

    @Modifying
    @Query("update VisitEntity v set v.state = :state where v.id = :id")
    void updateState(@Param(value = "id") Integer id, @Param(value = "state") State state);

    @Modifying
    @Query("update VisitEntity v set v.startTime = :startTime where v.id = :id")
    void updateStartTime(@Param(value = "id") Integer id, @Param(value = "startTime") String startTime);

    @Modifying
    @Query("update VisitEntity v set v.endTime = :endTime where v.id = :id")
    void updateEndTime(@Param(value = "id") Integer id, @Param(value = "endTime") String endTime);

    @Modifying
    @Query("update VisitEntity v set v.observations = :observations where v.id = :id")
    void updateObservations(@Param(value = "id") Integer id, @Param(value = "observations") String observations);

    @Modifying
    @Query("update VisitEntity v set v.exhibitors_counter = :exhibitor_counter where v.id = :id")
    void updateExhibitorCounter(@Param(value = "id") Integer id, @Param(value = "exhibitor_counter") Integer exhibitor_counter);

    @Modifying
    @Query("update VisitEntity v set v.timeSlotId = :timeSlotId where v.id = :id")
    void updateTimeSlotId(@Param(value = "id") Integer id,@Param(value = "timeSlotId") Integer timeSlotId);

}
