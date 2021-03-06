package uz.lc.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lc.db.entities.Truck;

import java.util.List;

public interface TruckRepository extends JpaRepository<Truck,Integer> {

    List<Truck> findAllByDeletedFalse();
    List<Truck> findAllByDeletedTrue();
    Truck findById(int id);

}
