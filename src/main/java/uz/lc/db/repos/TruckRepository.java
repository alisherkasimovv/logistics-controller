package uz.lc.db.repos;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.lc.db.entities.Truck;

import java.util.List;

public interface TruckRepository extends JpaRepository<Truck,Integer> {

    @Override
    List<Truck> findAll();
    Truck findById(int id);
    Truck findByName(String name);

}
