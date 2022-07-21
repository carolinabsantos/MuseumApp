package pt.ulisboa.tecnico.museumapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ulisboa.tecnico.museumapp.entities.BookingEntity;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    List<BookingEntity> findByDate(Date date);

    List<BookingEntity> findAll();
}
