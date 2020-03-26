package uz.lc.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lc.db.entities.Trailer;

import java.util.List;

public interface TrailerRepository extends JpaRepository<Trailer, Integer> {

    List<Trailer> findAllByDeletedTrue();
    List<Trailer> findAllByDeletedFalse();
    Trailer findById(int id);
    Trailer findByTruckId(int truckId);

}
