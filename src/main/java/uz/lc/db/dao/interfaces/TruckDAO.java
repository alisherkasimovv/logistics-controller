package uz.lc.db.dao.interfaces;

import uz.lc.collections.TruckAndMessage;
import uz.lc.db.entities.Truck;

import java.util.List;

public interface TruckDAO {
    List<Truck> get();
    Truck getById(int id);
    TruckAndMessage saveTruck(Truck truck);
    void deleteById(int id);
}
