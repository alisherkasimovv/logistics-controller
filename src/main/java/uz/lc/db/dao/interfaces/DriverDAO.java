package uz.lc.db.dao.interfaces;

import uz.lc.db.entities.Driver;

import java.util.List;

public interface DriverDAO {

    List<Driver> get();
    Driver getById(int id);
    void saveDriver(Driver driver);
    void deleteById(int id);
}
