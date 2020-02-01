package uz.lc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lc.collections.DriverAndMessage;
import uz.lc.db.dao.interfaces.DriverDAO;
import uz.lc.db.dao.interfaces.TruckDAO;
import uz.lc.db.entities.Driver;
import uz.lc.db.entities.Truck;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/drivers")
@CrossOrigin("http://localhost:3000")
public class DriverController {
    private DriverDAO driverDAO;

    @Autowired
    public DriverController(DriverDAO driverDAO) {
        this.driverDAO=driverDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return new ResponseEntity<>(driverDAO.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable int id) {
        return new ResponseEntity<>(driverDAO.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public HttpStatus deleteDriver(@PathVariable int id) {
        driverDAO.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<DriverAndMessage> saveDriver(@Valid @RequestBody Driver driver) {
        return new ResponseEntity<>(driverDAO.saveDriver(driver), HttpStatus.OK);
    }
}
