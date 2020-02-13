package uz.lc.db.dao.interfaces;

import uz.lc.dto.collections.TrackingAndMessage;
import uz.lc.db.entities.documents.Tracking;
import uz.lc.db.enums.TrackStatus;

import java.util.List;

public interface TrackingDAO {

    List<Tracking> getAll();
    List<Tracking> getAllCompleted();
    List<Tracking> getAllOnRoad();
    List<Tracking> getAllForSpecificCompany(Integer company);
    Tracking getById(Integer id);
    Tracking getByTrackingNumber(String trackNumber);
    TrackingAndMessage saveNewTracking(Tracking tracking);
    TrackingAndMessage editExistingTracking(Tracking tracking, TrackStatus newStatus);
    TrackingAndMessage updateStatusOfTheTracking(Tracking tracking);

    // Methods for delayed tracks
    TrackingAndMessage setTrackingAsDelayed(Tracking tracking);
    TrackingAndMessage setTrackingAsContinued(Tracking tracking);

}
