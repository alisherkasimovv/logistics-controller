package uz.lc.db.dao.interfaces;

import uz.lc.collections.UserAndMessage;
import uz.lc.db.entities.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();

    User getById(int id);

    User getByUsername(String username);

    UserAndMessage saveUser(User user);


    void deleteById(int id);
}
