package uz.lc.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lc.db.entities.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    List<Company> findAllByDeletedFalse();
    List<Company> findAllByDeletedTrue();
    Company findById(int id);
    Company findByName(String name);

}
