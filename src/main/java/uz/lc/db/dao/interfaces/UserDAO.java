package uz.lc.db.dao.interfaces;

import uz.lc.db.entities.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();

    User getById(int id);

    User getByUsername(String username);

    void saveUser(User user);

    //    void editUser(User user);
    void deleteById(int id);
}
