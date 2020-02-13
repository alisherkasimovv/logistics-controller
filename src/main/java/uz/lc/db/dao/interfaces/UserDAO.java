package uz.lc.db.dao.interfaces;

import uz.lc.dto.ReturningObjectAndMessage;
import uz.lc.db.entities.User;

import java.util.List;

public interface UserDAO {

    List<User> getAll();
    List<User> getAllUsersByType();
    User getById(int id);
    User getByUsername(String username);
    ReturningObjectAndMessage saveUser(User user);
    void deleteById(int id);

}
