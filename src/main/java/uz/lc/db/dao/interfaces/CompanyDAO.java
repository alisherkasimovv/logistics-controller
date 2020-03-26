package uz.lc.db.dao.interfaces;

import uz.lc.db.entities.Company;
import uz.lc.dto.CompanyWithDestinations;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

public interface CompanyDAO {

    List<Company> getAll();
    List<Company> getAllDeleted();
    List<CompanyWithDestinations> getAllCompaniesWithDestinations();
    CompanyWithDestinations getOneCompanyWithDestinations(int id);
    Company getById(int id);
    Company getByName(String name);
    ReturningObjectAndMessage saveCompany(Company company);
    void deleteById(int id);

}
