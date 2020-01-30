package uz.lc.db.dao.interfaces;

import uz.lc.db.entities.documents.DriverStatus;

import java.util.List;

public interface DriverStatusDAO {

    List<DriverStatus> getAllDriverStatuses();
    List<DriverStatus> getAllOnRoad();
    List<DriverStatus> getAllFree();
    void setDriverStatus(Integer id, String trackNumber);
    void createNewStatusForDriver(Integer id);

}
