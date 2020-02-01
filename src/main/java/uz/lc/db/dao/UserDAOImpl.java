package uz.lc.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lc.collections.UserAndMessage;
import uz.lc.db.dao.interfaces.UserDAO;
import uz.lc.db.entities.User;
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
    public User getById(int id) {
        return repository.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public UserAndMessage saveUser(User user) {
        User saved;
        UserAndMessage uam = new UserAndMessage();

        if (user.getId() != null) {
            User temp = this.getById(user.getId());

            temp.setPassword(user.getPassword());
            temp.setUsername(user.getUsername());
            temp.setType(user.getType());

            saved = repository.save(temp);
            uam.setMessage("User has been updated.");
        } else {
            saved = repository.save(user);
            uam.setMessage("New user has been saved.");
        }

        uam.setUser(saved);
        return uam;
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }


}
