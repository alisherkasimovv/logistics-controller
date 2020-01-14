package uz.lc.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lc.collections.CompanyAndMessage;
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
    public CompanyAndMessage saveCompany(Company company) {
        Company saved;

        if (company.getId() != null) {
            Company temp = this.getById(company.getId());

            temp.setName(company.getName());
            temp.setAddress(company.getAddress());
            temp.setLatitude(company.getLatitude());
            temp.setLongitude(company.getLongitude());

            saved = repository.save(temp);
        } else {
            saved = repository.save(company);
        }

        CompanyAndMessage cam = new CompanyAndMessage();
        cam.setCompany(saved);
        cam.setMessage("Company has been saved.");
        return cam;
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
