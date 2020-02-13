package uz.lc.db.dao.interfaces;

import uz.lc.dto.DriversWithStatuses;

import java.util.List;

public interface DriverWorkloadDAO {

    DriversWithStatuses getAllDriversSortedByTheirStatuses();
    void setDriverStatus(Integer id, String trackNumber, Integer trackingId);
    void createNewStatusForDriver(Integer id);

}
