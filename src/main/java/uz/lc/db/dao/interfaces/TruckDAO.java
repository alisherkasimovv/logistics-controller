package uz.lc.db.dao.interfaces;

import uz.lc.db.entities.Truck;
import uz.lc.dto.ReturningObjectAndMessage;
import uz.lc.dto.TruckDTO;

import java.util.List;

public interface TruckDAO {
    List<Truck> getAll();
    List<Truck> getAllDeleted();
    List<TruckDTO> getAllTrucksWithTrailers();
    TruckDTO getOneTruckWithTrailer(int id);
    Truck getById(int id);
    ReturningObjectAndMessage saveTruck(Truck truck);
    void deleteById(int id);
}
