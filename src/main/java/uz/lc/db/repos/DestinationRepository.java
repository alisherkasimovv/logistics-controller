package uz.lc.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lc.db.entities.Destination;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    List<Destination> findAllByDeletedFalse();
    List<Destination> findAllByCompanyId(Integer companyId);
    Destination findById(int id);

}
