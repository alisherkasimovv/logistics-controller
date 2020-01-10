package uz.lc.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lc.db.dao.interfaces.CompanyDAO;
import uz.lc.db.entities.Company;
import uz.lc.db.repos.CompanyRepository;

import java.util.List;

@Service
public class CompanyDAOImpl implements CompanyDAO {
    private CompanyRepository repository;

    @Autowired
    public CompanyDAOImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Company> getAll() {
        return repository.findAll();
    }

    @Override
    public Company getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Company getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void saveCompany(Company company) {
        repository.save(company);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
