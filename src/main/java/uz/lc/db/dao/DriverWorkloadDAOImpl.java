package uz.lc.db.dao;

import org.springframework.stereotype.Service;
import uz.lc.db.dao.interfaces.DriverWorkloadDAO;
import uz.lc.db.entities.documents.DriverWorkload;
import uz.lc.db.enums.DriverStatus;
import uz.lc.db.repos.DriverWorkloadRepository;
import uz.lc.dto.DriverDTO;
import uz.lc.dto.DriversWithStatuses;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DriverWorkloadDAOImpl implements DriverWorkloadDAO {

    private DriverWorkloadRepository repository;
    private EntityManager em;

    public DriverWorkloadDAOImpl(DriverWorkloadRepository repository) {
        this.repository = repository;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Method collects drivers and sorts them by their status.
     * Besides it counts all free and non-free drivers for further usage.
     *
     * @return Full collection of free and non-free drivers abd their count.
     */
    @Override
    @Transactional
    public DriversWithStatuses getAllDriversSortedByTheirStatuses() {
        DriversWithStatuses dws = new DriversWithStatuses();

        String SELECT_QUERY =
                "SELECT u.id, u.firstName, u.lastName, dw.driverStatus, dw.currentTrackingId " +
                "FROM User u " +
                "INNER JOIN DriverWorkload dw ON dw.driverId = u.id " +
                "WHERE u.userType = 'DRIVER'";
        Query query = em.createQuery(SELECT_QUERY);

        List<Object[]> resultList = query.getResultList();

        if (resultList.size() > 0) {
            for (Object[] obj : resultList) {
                DriverDTO dto = new DriverDTO();

                dto.setId((Integer) obj[0]);
                dto.setFirstName((String) obj[1]);
                dto.setLastName((String) obj[2]);
                dto.setCurrentStatus((DriverStatus) obj[3]);
                dto.setCurrentTrackingId((Integer) obj[4]);

                if (dto.getCurrentStatus() == DriverStatus.FREE) {
                    dws.setFreeDrivers(dto);
                } else {
                    dws.setNonFreeDrivers(dto);
                }
            }
        }

        dws.setNumberOfFreeDrivers(repository.countDriversByTheirStatus(DriverStatus.FREE));
        dws.setNumberOfNonFreeDrivers(repository.countDriversByTheirStatus(DriverStatus.ON_ROAD));

        return dws;
    }

    @Override
    public void setDriverStatus(Integer id, String trackNumber, Integer trackingId) {
        DriverWorkload driverWorkload = repository.findById((int) id);

        if (driverWorkload.getDriverStatus() == DriverStatus.FREE) {
            driverWorkload.setDriverStatus(DriverStatus.ON_ROAD);
            driverWorkload.setTrackNumber(trackNumber);
            driverWorkload.setCurrentTrackingId(trackingId);
        } else {
            driverWorkload.setDriverStatus(DriverStatus.FREE);
            driverWorkload.setTrackNumber("");
            driverWorkload.setCurrentTrackingId(null);
        }

        repository.save(driverWorkload);
    }

    @Override
    public void createNewStatusForDriver(Integer id) {
        DriverWorkload driverWorkload = new DriverWorkload();
        driverWorkload.setDriverId(id);
        driverWorkload.setDriverStatus(DriverStatus.FREE);
        driverWorkload.setTrackNumber("");
        driverWorkload.setCurrentTrackingId(null);

        repository.save(driverWorkload);
    }

}
