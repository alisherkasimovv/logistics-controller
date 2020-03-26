package uz.lc.db.dao;

import org.springframework.stereotype.Service;
import uz.lc.db.dao.interfaces.TrailerDAO;
import uz.lc.db.entities.Trailer;
import uz.lc.db.repos.TrailerRepository;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

@Service
public class TrailerDAOImpl implements TrailerDAO {

    private TrailerRepository repository;

    public TrailerDAOImpl(TrailerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Trailer> getAllTrailers() {
        return repository.findAllByDeletedTrue();
    }

    @Override
    public List<Trailer> getAllNonDeletedTrailers() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public Trailer getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Trailer getByTruckId(int truckId) {
        return repository.findByTruckId(truckId);
    }

    @Override
    public ReturningObjectAndMessage saveTrailer(Trailer trailer) {
        Trailer saved;
        ReturningObjectAndMessage roam = new ReturningObjectAndMessage();

        if (trailer.getId() != null) {
            Trailer temp = this.getById(trailer.getId());

            if (temp.getDeleted()) temp.setDeleted(false);
            if (trailer.getTruckId() != null) temp.setTruckId(trailer.getTruckId());

            temp.setTechnicalCertificate(trailer.getTechnicalCertificate());
            temp.setModel(trailer.getModel());
            temp.setCapacity(trailer.getCapacity());
            temp.setPlateNumber(trailer.getPlateNumber());

            roam.setMessage("Trailer has been updated.");
            saved = repository.save(temp);
        } else {
            roam.setMessage("New trailer has been created.");
            saved = repository.save(trailer);
        }

        roam.setReturningObject(saved);
        return roam;
    }
}
