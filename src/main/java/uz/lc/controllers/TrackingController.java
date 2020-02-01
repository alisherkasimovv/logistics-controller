package uz.lc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lc.collections.TrackingAndMessage;
import uz.lc.db.dao.interfaces.TrackingDAO;
import uz.lc.db.entities.documents.Tracking;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    private TrackingDAO trackingDAO;

    public TrackingController(TrackingDAO trackingDAO) {
        this.trackingDAO = trackingDAO;
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<Tracking>> getAllTracks() {
        return new ResponseEntity<>(trackingDAO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/on-road")
    public ResponseEntity<List<Tracking>> getAllTracksOnRoad() {
        return new ResponseEntity<>(trackingDAO.getAllOnRoad(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/done")
    public ResponseEntity<List<Tracking>> getAllTracksDone() {
        return new ResponseEntity<>(trackingDAO.getAllCompleted(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/company/{id}")
    public ResponseEntity<List<Tracking>> getAllTracksForCompany(@PathVariable int id) {
        return new ResponseEntity<>(trackingDAO.getAllForSpecificCompany(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get/tracking/{trackNo}")
    public ResponseEntity<Tracking> getByTrackingNumber(@PathVariable String trackNo) {
        return new ResponseEntity<>(trackingDAO.getByTrackingNumber(trackNo), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{trackingId}")
    public ResponseEntity<Tracking> getById(@PathVariable int trackingId) {
        return new ResponseEntity<>(trackingDAO.getById(trackingId), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<TrackingAndMessage> createNewTracking(@RequestBody Tracking tracking) {
        return new ResponseEntity<>(trackingDAO.saveNewTracking(tracking), HttpStatus.OK);
    }

    @PostMapping(value = "/update/status")
    public ResponseEntity<TrackingAndMessage> updateStatusOfTracking(@RequestBody Tracking tracking) {
        return new ResponseEntity<>(trackingDAO.updateStatusOfTheTracking(tracking), HttpStatus.OK);
    }

    @PostMapping(value = "/update/delayed")
    public ResponseEntity<TrackingAndMessage> updateStatusOfTrackingToDelayed(@RequestBody Tracking tracking) {
        return new ResponseEntity<>(trackingDAO.setTrackingAsDelayed(tracking), HttpStatus.OK);
    }

    @PostMapping(value = "/update/continued")
    public ResponseEntity<TrackingAndMessage> updateStatusOfTrackingToContinued(@RequestBody Tracking tracking) {
        return new ResponseEntity<>(trackingDAO.setTrackingAsContinued(tracking), HttpStatus.OK);
    }

}
