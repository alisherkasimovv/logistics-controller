package uz.lc.db.dao;

import org.springframework.stereotype.Service;
import uz.lc.db.dao.interfaces.DestinationDAO;
import uz.lc.db.entities.Destination;
import uz.lc.db.repos.DestinationRepository;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

@Service
public class DestinationDAOImpl implements DestinationDAO {

    private DestinationRepository repository;

    public DestinationDAOImpl(DestinationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Destination> getAllDestinations() {
        return repository.findAll();
    }

    @Override
    public List<Destination> getAllDestinationsForParticularCompany(Integer companyId) {
        return repository.findAllByCompanyId(companyId);
    }

    @Override
    public Destination getOneDestination(int id) {
        return repository.findById(id);
    }

    @Override
    public ReturningObjectAndMessage saveDestination(Destination destination) {
        Destination saved;
        ReturningObjectAndMessage roam = new ReturningObjectAndMessage();

        if (destination.getId() != null) {
            Destination temp = this.getOneDestination(destination.getId());

            temp.setAddress(destination.getAddress());
            temp.setLatitude(destination.getLatitude());
            temp.setLongitude(destination.getLongitude());
            temp.setCompanyId(destination.getCompanyId());

            saved = repository.save(temp);
            roam.setMessage("Destination has been updated.");
        } else {
            saved = repository.save(destination);
            roam.setMessage("New destination has been saved.");
        }

        roam.setReturningObject(saved);
        return roam;
    }
}
