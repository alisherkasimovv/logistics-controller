package uz.lc.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lc.db.entities.User;
import uz.lc.db.enums.UserType;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();
    List<User> findAllByDeletedFalse();
    List<User> findAllByDeletedFalseAndUserType(UserType type);
    User findById(int id);
    User findByUsername(String username);

}
