package uz.lc.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.lc.db.entities.documents.DriverWorkload;
import uz.lc.db.enums.DriverStatus;

import java.util.List;

public interface DriverWorkloadRepository extends JpaRepository<DriverWorkload, Integer> {

    List<DriverWorkload> findAllByDeletedFalse();
    List<DriverWorkload> findAllByDriverStatusAndDeletedFalse(DriverStatus driverStatus);
    DriverWorkload findById(int id);

    @Query("SELECT count(ds.id) FROM DriverWorkload ds WHERE ds.driverStatus = :driverStatus")
    int countDriversByTheirStatus(DriverStatus driverStatus);

}
