package uz.lc.db.dao.interfaces;

import uz.lc.db.entities.Company;


import java.util.List;

public interface CompanyDAO {

    List<Company> getAll();

    Company getById(int id);

    Company getByName(String name);

    void saveCompany(Company company);

//    void editCompany(Company company);

    void deleteById(int id);


}
