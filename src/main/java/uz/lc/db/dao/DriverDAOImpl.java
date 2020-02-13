package uz.lc.db.dao;

import org.springframework.stereotype.Service;
import uz.lc.db.dao.interfaces.DriverWorkloadDAO;
import uz.lc.db.dao.interfaces.UserDAO;
import uz.lc.db.entities.User;
import uz.lc.db.enums.UserType;
import uz.lc.db.repos.UserRepository;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

@Service
public class DriverDAOImpl implements UserDAO {
    private UserRepository repository;
    private DriverWorkloadDAO statusDAO;

    public  DriverDAOImpl(UserRepository repository, DriverWorkloadDAO statusDAO){
        this.repository = repository;
        this.statusDAO = statusDAO;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public List<User> getAllUsersByType() {
        return repository.findAllByDeletedFalseAndUserType(UserType.DRIVER);
    }

    @Override
    public User getById(int id) {
        return repository.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public ReturningObjectAndMessage saveUser(User user) {
        User saved;
        ReturningObjectAndMessage roam = new ReturningObjectAndMessage();

        if (user.getId() != null) {
            User temp = this.getById(user.getId());

            temp.setFirstName(user.getFirstName());
            temp.setLastName(user.getLastName());
            temp.setUserType(UserType.DRIVER);

            saved = repository.save(temp);
            roam.setMessage("Driver has been updated.");
        } else {
            user.setUserType(UserType.DRIVER);
            saved = repository.save(user);
            roam.setMessage("New driver has been saved.");
        }

        statusDAO.createNewStatusForDriver(saved.getId());

        roam.setReturningObject(saved);
        return roam;
    }

    @Override
    public void deleteById(int id) {
        User user=repository.findById(id);
        user.setDeleted(true);
        repository.save(user);
    }
}
