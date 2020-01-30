package uz.lc.db.dao;

import org.springframework.stereotype.Service;
import uz.lc.db.dao.interfaces.DriverStatusDAO;
import uz.lc.db.entities.documents.DriverStatus;
import uz.lc.db.enums.Status;
import uz.lc.db.repos.DriverStatusRepository;

import java.util.List;

@Service
public class DriverStatusDAOImpl implements DriverStatusDAO {

    private DriverStatusRepository repository;

    public DriverStatusDAOImpl(DriverStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DriverStatus> getAllDriverStatuses() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public List<DriverStatus> getAllOnRoad() {
        return repository.findAllByDriverStatusAndDeletedFalse(Status.ON_ROAD);
    }

    @Override
    public List<DriverStatus> getAllFree() {
        return repository.findAllByDriverStatusAndDeletedFalse(Status.FREE);
    }

    @Override
    public void setDriverStatus(Integer id, String trackNumber) {
        DriverStatus driverStatus = repository.findById((int) id);

        if (driverStatus.getDriverStatus() == Status.FREE) {
            driverStatus.setDriverStatus(Status.ON_ROAD);
            driverStatus.setTrackNumber(trackNumber);
        } else {
            driverStatus.setDriverStatus(Status.FREE);
            driverStatus.setTrackNumber("");
        }

    }

    @Override
    public void createNewStatusForDriver(Integer id) {
        DriverStatus status = new DriverStatus();
        status.setDriverId(id);
        status.setDriverStatus(Status.FREE);
        status.setTrackNumber("");

        repository.save(status);
    }
}
