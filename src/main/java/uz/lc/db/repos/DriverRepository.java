package uz.lc.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lc.db.entities.Driver;

import java.util.List;

public interface DriverRepository  extends JpaRepository<Driver,Integer> {
    @Override
    List<Driver> findAll();
    Driver findById(int id);
    Driver findByName(String name);

}
