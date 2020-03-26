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
    public List<User> getAllNonDeleted() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public List<User> getAllDeleted() {
        return repository.findAllByDeletedTrue();
    }

    @Override
    public List<User> getAllUsersByType(UserType type) {
        return repository.findAllByDeletedFalseAndUserType(type);
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

            temp.setUsername(user.getUsername());
            temp.setPassword(user.getPassword());
            temp.setFirstName(user.getFirstName());
            temp.setLastName(user.getLastName());
            temp.setUserType(user.getUserType());

            saved = saveParticularUser(temp);
            roam.setMessage("User has been updated.");
        } else {
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
