package pt.ulisboa.tecnico.museumapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ulisboa.tecnico.museumapp.entities.QRCodeEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface QRCodeRepository extends JpaRepository<QRCodeEntity, Integer> {
    public List<QRCodeEntity> findAll();

}
