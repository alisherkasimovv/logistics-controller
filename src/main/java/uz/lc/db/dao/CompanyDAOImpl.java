package uz.lc.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lc.db.dao.interfaces.CompanyDAO;
import uz.lc.db.dao.interfaces.DestinationDAO;
import uz.lc.db.entities.Company;
import uz.lc.db.entities.Destination;
import uz.lc.db.repos.CompanyRepository;
import uz.lc.dto.CompanyWithDestinations;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyDAOImpl implements CompanyDAO {
    private CompanyRepository repository;
    private DestinationDAO destinationDAO;

    @Autowired
    public CompanyDAOImpl(CompanyRepository repository, DestinationDAO destinationDAO) {
        this.repository = repository;
        this.destinationDAO = destinationDAO;
    }

    @Override
    public List<Company> getAll() {
        return repository.findAll();
    }

    @Override
    public List<CompanyWithDestinations> getAllCompaniesWithDestinations() {
        List<Company> companies = this.getAll();
        List<CompanyWithDestinations> cwdList = new ArrayList<>();

        for (Company company : companies) {
            CompanyWithDestinations cwd = new CompanyWithDestinations();
            cwd.setCompany(company);
            cwd.setDestinations(destinationDAO.getAllDestinationsForParticularCompany(company.getId()));

            cwdList.add(cwd);
        }

        return cwdList;
    }

    @Override
    public CompanyWithDestinations getOneCompanyWithDestinations(int id) {
        Company company = this.getById(id);
        List<Destination> destinations = destinationDAO.getAllDestinationsForParticularCompany(company.getId());

        CompanyWithDestinations cwd = new CompanyWithDestinations();
        cwd.setCompany(company);
        cwd.setDestinations(destinations);

        return cwd;
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
    public ReturningObjectAndMessage saveCompany(Company company) {
        Company saved;
        ReturningObjectAndMessage roam = new ReturningObjectAndMessage();

        if (company.getId() != null) {
            Company temp = this.getById(company.getId());

            temp.setName(company.getName());
            temp.setAddress(company.getAddress());

            saved = repository.save(temp);
            roam.setMessage("Company has been updated.");
        } else {
            saved = repository.save(company);
            roam.setMessage("New company has been saved.");
        }

        roam.setReturningObject(saved);
        return roam;
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
