package uz.lc.db.dao.interfaces;

import uz.lc.db.entities.Destination;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

public interface DestinationDAO {

    List<Destination> getAllDestinations();
    List<Destination> getAllDestinationsForParticularCompany(Integer companyId);
    Destination getOneDestination(int id);
    ReturningObjectAndMessage saveDestination(Destination destination);

}
