package uz.lc.db.dao.interfaces;

import uz.lc.db.entities.Trailer;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

public interface TrailerDAO {

    List<Trailer> getAllTrailers();
    List<Trailer> getAllNonDeletedTrailers();
    Trailer getById(int id);
    Trailer getByTruckId(int truckId);
    ReturningObjectAndMessage saveTrailer(Trailer trailer);

}
