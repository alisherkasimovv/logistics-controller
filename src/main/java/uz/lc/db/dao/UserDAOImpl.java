package uz.lc.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import uz.lc.dto.ReturningObjectAndMessage;
import uz.lc.db.dao.interfaces.UserDAO;
import uz.lc.db.entities.User;
import uz.lc.db.enums.UserType;
import uz.lc.db.repos.UserRepository;

import java.util.List;

@Service
public class UserDAOImpl implements UserDAO {
    private UserRepository repository;

    @Autowired
    public UserDAOImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public List<User> getAllUsersByType() {
        return repository.findAllByDeletedFalseAndUserType(UserType.AGENT);
    }

    @Override
    public User getById(int id) {
        return repository.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public ReturningObjectAndMessage saveUser(User user) {
        User saved;
        ReturningObjectAndMessage roam = new ReturningObjectAndMessage();

        if (user.getId() != null) {
            User temp = this.getById(user.getId());

            temp.setPassword(user.getPassword());
            temp.setUsername(user.getUsername());
            temp.setUserType(UserType.AGENT);

            saved = saveParticularUser(temp);
            roam.setMessage("User has been updated.");
        } else {
            user.setUserType(UserType.AGENT);
            saved = saveParticularUser(user);
            roam.setMessage("New user has been saved.");
        }

        if (saved == null) roam.setMessage("This username has already taken. Please, enter new.");
        roam.setReturningObject(saved);
        return roam;
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    private User saveParticularUser(User user) {
        User u;
        try {
            u = repository.save(user);
        } catch (DataIntegrityViolationException ex) {
            return null;
        }
        return u;
    }
}
