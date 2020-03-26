package uz.lc.db.dao.interfaces;

import uz.lc.db.enums.UserType;
import uz.lc.dto.ReturningObjectAndMessage;
import uz.lc.db.entities.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllNonDeleted();
    List<User> getAllDeleted();
    List<User> getAllUsersByType(UserType type);
    User getById(int id);
    User getByUsername(String username);
    ReturningObjectAndMessage saveUser(User user);
    void deleteById(int id);

}
