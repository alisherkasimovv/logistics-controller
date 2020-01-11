package uz.lc.db.dao;

import org.springframework.stereotype.Service;
import uz.lc.collections.TrackingAndMessage;
import uz.lc.configs.TrackNumberCreator;
import uz.lc.db.dao.interfaces.DriverDAO;
import uz.lc.db.dao.interfaces.TrackingDAO;
import uz.lc.db.entities.Driver;
import uz.lc.db.entities.documents.Tracking;
import uz.lc.db.enums.Region;
import uz.lc.db.enums.TrackStatus;
import uz.lc.db.repos.TrackingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackingDAOImpl implements TrackingDAO {

    private int reference = 0;
    private TrackingRepository repository;
    private DriverDAO driverDAO;

    public TrackingDAOImpl(TrackingRepository repository, DriverDAO driverDAO) {
        this.repository = repository;
        this.driverDAO = driverDAO;
    }

    @Override
    public List<Tracking> getAll() {
        return repository.findAllByDeletedFalseOrderByDateCreatedAsc();
    }

    @Override
    public List<Tracking> getAllCompleted() {
        return repository.findAllByJobDoneTrueAndDeletedFalseOrderByDateCreatedAsc();
    }

    @Override
    public List<Tracking> getAllOnRoad() {
        return repository.findAllByJobDoneFalseAndDeletedFalseOrderByDateCreatedAsc();
    }

    @Override
    public List<Tracking> getAllForSpecificCompany(Integer company) {
        return repository.findAllByCompanyIdAndJobDoneFalseAndDeletedFalseOrderByDateCreatedAsc(company);
    }

    @Override
    public Tracking getById(Integer id) {
        return null;
    }

    @Override
    public Tracking getByTrackingNumber(String trackNumber) {
        return repository.findByTrackNumber(trackNumber);
    }

    @Override
    public TrackingAndMessage saveNewTracking(Tracking tracking) {
        String message = "";

        if (tracking.getDriverId() != null) {
            Driver driver = driverDAO.getById(tracking.getDriverId());

            tracking.setStatus(TrackStatus.ACCEPTED);

            // Creating string from driver initials according to the information from DB
            // If first and last names are not present will be used driver's username.
            String driverInitials = "";
            if (driver.getFirstName() != null && driver.getLastName() != null) {
                driverInitials = driver.getFirstName().charAt(0) + "" + driver.getLastName().charAt(0);
                driverInitials = driverInitials.toUpperCase();
            } else {
                driverInitials = driver.getName().substring(0,2).toUpperCase();
            }

            if (driver.getTruckId() == null) driver.setTruckId(0);
            tracking.setTrackNumber(this.createTrackNumber(
                    driverInitials,
                    driver.getTruckId(),
                    LocalDateTime.now(),
                    tracking.getRegion()
            ));

            message = "Tracking has been added." +
                    "<br>It's status changed to ACCEPTED." +
                    "<br>Tracking No. has been generated: " + tracking.getTrackNumber();
        } else {
            tracking.setStatus(TrackStatus.PENDING);
            message = "Tracking has been added." +
                    "<br>It's status changed to PENDING." +
                    "<br>Tracking No. will be generated right after accepting this track by one of the free drivers.";
        }
        Tracking track = repository.save(tracking);

        TrackingAndMessage tam = new TrackingAndMessage();
        tam.setTracking(track);
        tam.setMessage(message);
        return tam;
    }

    @Override
    public TrackingAndMessage editExistingTracking(Tracking tracking) {
        return null;
    }

    @Override
    public TrackingAndMessage updateStatusOfTheTracking(TrackStatus trackStatus) {
        return null;
    }

    private String createTrackNumber(String initials, int truckId, LocalDateTime dateTime, Region region) {
        TrackNumberCreator tnc = new TrackNumberCreator();
        tnc.setDateTime(dateTime);
        tnc.setDriver(initials);
        tnc.setTruckId(truckId);

        if (region == null) region = Region.TAS;
        tnc.setRegion(region);

        if (reference == 1000) reference = 0;
        tnc.setReference(reference);
        ++reference;

        return tnc.generateTrackNumber();
    }
}
