package uz.lc.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lc.db.dao.interfaces.TruckDAO;
import uz.lc.db.entities.Truck;
import uz.lc.db.repos.TruckRepository;
import uz.lc.dto.ReturningObjectAndMessage;

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
    public ReturningObjectAndMessage saveTruck(Truck truck) {
        Truck saved;
        ReturningObjectAndMessage roam = new ReturningObjectAndMessage();

        if (truck.getId() != null) {
            Truck temp = this.getById(truck.getId());

            temp.setModel(truck.getModel());
            temp.setPlateNumber(truck.getPlateNumber());

            saved = repository.save(temp);
            roam.setMessage("Truck with id - " + saved.getId() + " has been updated.");
        } else {
            saved = repository.save(truck);
            roam.setMessage("New truck has been saved.");
        }

        roam.setReturningObject(saved);
        return roam;
    }

    @Override
    public void deleteById(int id) {
        Truck truck=repository.findById(id);
        truck.setDeleted(true);
        repository.save(truck);
    }
}
