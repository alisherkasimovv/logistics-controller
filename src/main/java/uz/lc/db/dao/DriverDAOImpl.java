package uz.lc.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lc.collections.DriverAndMessage;
import uz.lc.db.dao.interfaces.DriverDAO;
import uz.lc.db.dao.interfaces.DriverStatusDAO;
import uz.lc.db.entities.Driver;
import uz.lc.db.repos.DriverRepository;

import java.util.List;

@Service
public class DriverDAOImpl implements DriverDAO {
    private DriverRepository repository;
    private DriverStatusDAO statusDAO;

    @Autowired
    public  DriverDAOImpl(DriverRepository driverRepository, DriverStatusDAO statusDAO){
        repository=driverRepository;
        this.statusDAO = statusDAO;
    }

    @Override
    public List<Driver> get() {
        return repository.findAll();
    }

    @Override
    public Driver getById(int id) {
        return repository.findById(id);
    }

    @Override
    public DriverAndMessage saveDriver(Driver driver) {
        Driver saved;
        DriverAndMessage dam = new DriverAndMessage();

        if (driver.getId() != null) {
            Driver temp = this.getById(driver.getId());

            temp.setFirstName(driver.getFirstName());
            temp.setLastName(driver.getLastName());
            temp.setName(driver.getName());

            saved = repository.save(temp);
            dam.setMessage("Driver has been updated.");
        } else {
            saved = repository.save(driver);
            dam.setMessage("New driver has been saved.");
        }

        statusDAO.createNewStatusForDriver(saved.getId());

        dam.setDriver(saved);
        return dam;
    }

    @Override
    public void deleteById(int id) {

        Driver driver=repository.findById(id);
        driver.setDeleted(true);
        repository.save(driver);
    }
}
