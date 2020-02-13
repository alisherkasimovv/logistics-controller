package uz.lc.db.dao.interfaces;

import uz.lc.db.entities.Truck;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

public interface TruckDAO {
    List<Truck> get();
    Truck getById(int id);
    ReturningObjectAndMessage saveTruck(Truck truck);
    void deleteById(int id);
}
