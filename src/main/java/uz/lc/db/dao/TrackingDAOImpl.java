package uz.lc.db.dao;

import org.springframework.stereotype.Service;
import uz.lc.configs.TrackNumberCreator;
import uz.lc.db.dao.interfaces.DriverWorkloadDAO;
import uz.lc.db.dao.interfaces.TrackingDAO;
import uz.lc.db.dao.interfaces.UserDAO;
import uz.lc.db.entities.User;
import uz.lc.db.entities.documents.Tracking;
import uz.lc.db.enums.Region;
import uz.lc.db.enums.TrackStatus;
import uz.lc.db.repos.TrackingRepository;
import uz.lc.dto.ReturningObjectAndMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// TODO Region definition should be changed
@Service
public class TrackingDAOImpl implements TrackingDAO {

    private int reference = 0;
    private TrackingRepository repository;
    private UserDAO driverDAO;
    private DriverWorkloadDAO driverWorkloadDAO;

    public TrackingDAOImpl(TrackingRepository repository, UserDAO driverDAO, DriverWorkloadDAO driverWorkloadDAO) {
        this.repository = repository;
        this.driverDAO = driverDAO;
        this.driverWorkloadDAO = driverWorkloadDAO;
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

    /**
     * This method will called when new tracking order opened.
     * Before saving firstly it checks emptiness of the driverId field, according to which
     * tracking status will be automatically chosen. If field is empty system will save tracking
     * as pending, otherwise tracking will become ACCEPTED by particular driver.
     *
     * @param tracking Tracking info from agent.
     * @return Updated tracking info and a message.
     */
    @Override
    public ReturningObjectAndMessage saveNewTracking(Tracking tracking) {
        String message;

        if (tracking.getDriverId() != null) {
            User driver = driverDAO.getById(tracking.getDriverId());

            tracking.setStatus(TrackStatus.ACCEPTED);
            tracking.setDateCreated(LocalDateTime.now());
            tracking.setDateAccepted(LocalDateTime.now());

            // Creating string from driver initials according to the information from DB
            // If first and last names are not present will be used driver's username.
            String driverInitials;
            if (driver.getFirstName() != null && driver.getLastName() != null) {
                driverInitials = driver.getFirstName().charAt(0) + "" + driver.getLastName().charAt(0);
                driverInitials = driverInitials.toUpperCase();
            } else driverInitials = driver.getUsername().substring(0, 2).toUpperCase();

            if (driver.getTruckId() == null) driver.setTruckId(0);
            tracking.setTrackNumber(this.createTrackNumber(
                    driverInitials,
                    driver.getTruckId(),
                    LocalDateTime.now(),
                    Region.TAS
            ));

            driverWorkloadDAO.setDriverStatus(driver.getId(), tracking.getTrackNumber(), tracking.getId());
            message = "Tracking has been added." +
                    "<br>It's status changed to ACCEPTED." +
                    "<br>Tracking No. has been generated: " + tracking.getTrackNumber();
        } else {
            tracking.setStatus(TrackStatus.PENDING);
            tracking.setDateCreated(LocalDateTime.now());
            message = "Tracking has been added." +
                    "<br>It's status changed to PENDING." +
                    "<br>Tracking No. will be generated right after accepting this track by one of the free drivers.";
        }
        Tracking track = repository.save(tracking);

        ReturningObjectAndMessage tam = new ReturningObjectAndMessage();
        tam.setReturningObject(track);
        tam.setMessage(message);
        return tam;
    }

    @Override
    public ReturningObjectAndMessage editExistingTracking(Tracking tracking, TrackStatus newStatus) {
        return null;
    }

    /**
     * Method sets tracking as delayed due to issues may happen during delivery.
     * Delaying process requires information from driver, which will be used for reports
     * for other users of the system.
     * There will be gotten timestamp showing the time of delay start, then delivery status
     * will be set to <b>DELAYED</b>. Before that method saves previous status to <i>StatusBeforeDelay</i>
     * field.
     *
     * @param tracking Tracking info from driver.
     * @return Updated tracking info and a message.
     */
    @Override
    public ReturningObjectAndMessage setTrackingAsDelayed(Tracking tracking) {
        Tracking delayingTrack = repository.findByTrackNumber(tracking.getTrackNumber());

        delayingTrack.setStatusBeforeDelay(delayingTrack.getStatus());
        delayingTrack.setStatus(TrackStatus.DELAYED);
        delayingTrack.setDateDelayed(LocalDateTime.now());
        delayingTrack.setDelayReason(tracking.getDelayReason());

        Tracking saved = repository.save(delayingTrack);

        ReturningObjectAndMessage tam = new ReturningObjectAndMessage();
        tam.setReturningObject(saved);
        assert saved.getDateDelayed() != null;
        tam.setMessage("Tracking " + saved.getTrackNumber() + " has been delayed at "
                + saved.getDateDelayed().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ".");
        return tam;
    }

    /**
     * Method sets tracking as continuing after delay.
     * Would be called when delayed tracking will be continued. This method also saves timestamp
     * of status changing moment. Current status will be changed from DELAYED to that which
     * was before delay.
     *
     * @param tracking Tracking from driver.
     * @return Updated tracking info and a message.
     */
    @Override
    public ReturningObjectAndMessage setTrackingAsContinued(Tracking tracking) {
        Tracking continuingTrack = repository.findByTrackNumber(tracking.getTrackNumber());

        continuingTrack.setStatus(continuingTrack.getStatusBeforeDelay());
        continuingTrack.setDateContinued(LocalDateTime.now());

        Tracking saved = repository.save(continuingTrack);

        ReturningObjectAndMessage tam = new ReturningObjectAndMessage();
        tam.setReturningObject(saved);

        assert saved.getDateDelayed() != null;
        tam.setMessage("Tracking " + saved.getTrackNumber() + " has been continued at "
                + saved.getDateDelayed().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + " with status " + saved.getStatus() +".");
        return tam;
    }

    @Override
    public ReturningObjectAndMessage updateStatusOfTheTracking(Tracking tracking) {
        Tracking updatingTrack = repository.findByTrackNumber(tracking.getTrackNumber());

        assert tracking.getDriverId() != null;
        User driver = driverDAO.getById(tracking.getDriverId());

        switch (tracking.getStatus()) {
            case PENDING:
                updatingTrack.setStatus(TrackStatus.ACCEPTED);
                updatingTrack.setDriverId(tracking.getDriverId());
                updatingTrack.setDateAccepted(LocalDateTime.now());

                // Creating string from driver initials according to the information from DB
                // If first and last names are not present will be used driver's username.
                String driverInitials;
                if (driver.getFirstName() != null && driver.getLastName() != null) {
                    driverInitials = driver.getFirstName().charAt(0) + "" + driver.getLastName().charAt(0);
                    driverInitials = driverInitials.toUpperCase();
                } else driverInitials = driver.getUsername().substring(0, 2).toUpperCase();

                if (driver.getTruckId() == null) driver.setTruckId(0);
                tracking.setTrackNumber(this.createTrackNumber(
                        driverInitials,
                        driver.getTruckId(),
                        LocalDateTime.now(),
                        Region.TAS
                ));

                driverWorkloadDAO.setDriverStatus(driver.getId(), tracking.getTrackNumber(), tracking.getId());

                break;

            case ACCEPTED:
                updatingTrack.setStatus(TrackStatus.LOADING);
                updatingTrack.setDateAccepted(LocalDateTime.now());
                break;

            case LOADING:
                updatingTrack.setStatus(TrackStatus.GOING);
                updatingTrack.setDateLoaded(LocalDateTime.now());
                break;

            case GOING:
                updatingTrack.setStatus(TrackStatus.SHIPPING);
                updatingTrack.setDateGoing(LocalDateTime.now());
                break;

            case SHIPPING:
                updatingTrack.setStatus(TrackStatus.DONE);
                updatingTrack.setDateShipped(LocalDateTime.now());
                updatingTrack.setDateDone(LocalDateTime.now());
                updatingTrack.setJobDone(true);

                driverWorkloadDAO.setDriverStatus(driver.getId(), tracking.getTrackNumber(), tracking.getId());

                break;
        }
        Tracking saved = repository.save(updatingTrack);

        ReturningObjectAndMessage tam = new ReturningObjectAndMessage();
        tam.setReturningObject(saved);

        assert saved.getDateDelayed() != null;
        tam.setMessage("Tracking status for " + saved.getTrackNumber() +" has been updated to " + saved.getStatus());
        return tam;
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
