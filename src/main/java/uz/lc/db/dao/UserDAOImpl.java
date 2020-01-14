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

        if (user.getId() != null) {
            User temp = this.getById(user.getId());

            temp.setPassword(user.getPassword());
            temp.setUsername(user.getUsername());
            temp.setType(user.getType());

            saved = repository.save(temp);
        } else {
            saved = repository.save(user);
        }

        UserAndMessage uam = new UserAndMessage();

        uam.setUser(saved);
        uam.setMessage("User has been saved.");
        return uam;
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }


}
