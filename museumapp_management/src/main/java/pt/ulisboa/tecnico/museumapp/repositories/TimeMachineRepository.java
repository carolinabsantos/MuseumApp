package pt.ulisboa.tecnico.museumapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.TypeOfTimeMachine;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TimeMachineRepository extends JpaRepository<TimeMachineEntity, Integer> {
    public List<TimeMachineEntity> findAll();

    Optional<TimeMachineEntity> findByNameEquals(String name);

    @Modifying
    @Query("update TimeMachineEntity t set t.capacity = :capacity where t.id = :id")
    void updateTimeMachineCapacity(@Param(value = "id") Integer id, @Param(value = "capacity") Integer capacity);

    @Modifying
    @Query("update TimeMachineEntity t set t.type = :type where t.id = :id")
    void updateTimeMachineType(@Param(value = "id") Integer id, @Param(value = "type") TypeOfTimeMachine type);

    @Modifying
    @Query("update TimeMachineEntity t set t.image = :image where t.id = :id")
    void updateTimeMachineImage(@Param(value = "id") Integer id, @Param(value = "image") String image);

}
