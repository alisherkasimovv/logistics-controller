package uz.lc.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lc.collections.DriverAndMessage;
import uz.lc.db.dao.interfaces.DriverDAO;
import uz.lc.db.dao.interfaces.TruckDAO;
import uz.lc.db.entities.Driver;
import uz.lc.db.repos.DriverRepository;

import java.util.List;

@Service
public class DriverDAOImpl implements DriverDAO {
    private DriverRepository repository;

    @Autowired
    public  DriverDAOImpl(DriverRepository driverRepository){
        repository=driverRepository;
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

        if (driver.getId() != null) {
            Driver temp = this.getById(driver.getId());

            temp.setFirstName(driver.getFirstName());
            temp.setLastName(driver.getLastName());
            temp.setName(driver.getName());

            saved = repository.save(temp);
        } else {
            saved = repository.save(driver);
        }
        DriverAndMessage dam = new DriverAndMessage();
        dam.setDriver(saved);
        dam.setMessage("Driver has been saved.");
        return dam;
    }

    @Override
    public void deleteById(int id) {

        Driver driver=repository.findById(id);
        driver.setDeleted(true);
        repository.save(driver);
    }
}
