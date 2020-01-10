package uz.lc.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lc.db.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    User findById(int id);

    User findByUsername(String username);

}
