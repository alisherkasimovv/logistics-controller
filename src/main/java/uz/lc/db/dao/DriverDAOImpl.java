package uz.lc.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lc.db.dao.interfaces.DriverDAO;
import uz.lc.db.dao.interfaces.TruckDAO;
import uz.lc.db.entities.Driver;
import uz.lc.db.repos.DriverRepository;

import java.util.List;

@Service
public class DriverDAOImpl implements DriverDAO {
    private DriverRepository repository;
    private TruckDAO truckDAO;

    @Autowired
    public  DriverDAOImpl(DriverRepository driverRepository,TruckDAO truckDAO){
        repository=driverRepository;
        this.truckDAO=truckDAO;
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
    public void saveDriver(Driver driver) {

        repository.save(driver);

    }

    @Override
    public void deleteById(int id) {

        Driver driver=repository.findById(id);
        driver.setDeleted(true);
        repository.save(driver);
    }
}
