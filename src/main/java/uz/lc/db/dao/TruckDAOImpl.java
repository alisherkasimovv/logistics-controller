package uz.lc.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lc.db.dao.interfaces.DriverDAO;
import uz.lc.db.dao.interfaces.TruckDAO;
import uz.lc.db.entities.Truck;
import uz.lc.db.repos.TruckRepository;

import java.util.List;
@Service
public class TruckDAOImpl implements TruckDAO{
    private TruckRepository repository;

    @Autowired
    public TruckDAOImpl(TruckRepository truckRepository){
        this.repository=truckRepository;
    }

    @Override
    public List<Truck> get() {
        return repository.findAll();
    }

    @Override
    public Truck getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void saveTruck(Truck truck) {
        repository.save(truck);
    }

    @Override
    public void deleteById(int id) {
        Truck truck=repository.findById(id);
        truck.setDeleted(true);
        repository.save(truck);
    }
}
