package uz.lc.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lc.db.entities.documents.DriverStatus;
import uz.lc.db.enums.Status;

import java.util.List;

public interface DriverStatusRepository extends JpaRepository<DriverStatus, Integer> {

    List<DriverStatus> findAllByDeletedFalse();
    List<DriverStatus> findAllByDriverStatusAndDeletedFalse(Status status);
    DriverStatus findById(int id);

}
