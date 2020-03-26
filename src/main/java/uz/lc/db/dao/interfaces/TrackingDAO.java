package uz.lc.db.dao.interfaces;

import uz.lc.db.entities.documents.Tracking;
import uz.lc.db.enums.TrackStatus;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

public interface TrackingDAO {

    List<Tracking> getAll();
    List<Tracking> getAllCompleted();
    List<Tracking> getAllOnRoad();
    List<Tracking> getAllForSpecificCompany(Integer company);
    Tracking getById(Integer id);
    Tracking getByTrackingNumber(String trackNumber);
    ReturningObjectAndMessage saveNewTracking(Tracking tracking);
    ReturningObjectAndMessage editExistingTracking(Tracking tracking, TrackStatus newStatus);
    ReturningObjectAndMessage updateStatusOfTheTracking(Tracking tracking);

    // Methods for delayed tracks
    ReturningObjectAndMessage setTrackingAsDelayed(Tracking tracking);
    ReturningObjectAndMessage setTrackingAsContinued(Tracking tracking);

}
