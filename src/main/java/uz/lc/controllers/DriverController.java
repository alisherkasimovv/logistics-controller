package uz.lc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lc.db.dao.DriverDAOImpl;
import uz.lc.db.dao.interfaces.DriverWorkloadDAO;
import uz.lc.db.dao.interfaces.UserDAO;
import uz.lc.db.entities.User;
import uz.lc.dto.DriversWithStatuses;
import uz.lc.dto.ReturningObjectAndMessage;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/drivers")
@CrossOrigin("http://localhost:3000")
public class DriverController {

    private UserDAO userDAO;
    private DriverWorkloadDAO workloadDAO;

    @Autowired
    public DriverController(DriverDAOImpl userDAO, DriverWorkloadDAO workloadDAO) {
        this.userDAO = userDAO;
        this.workloadDAO = workloadDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<User>> getAllDrivers() {
        return new ResponseEntity<>(userDAO.getAllUsersByType(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getDriver(@PathVariable int id) {
        return new ResponseEntity<>(userDAO.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public HttpStatus deleteDriver(@PathVariable int id) {
        userDAO.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ReturningObjectAndMessage> saveDriver(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userDAO.saveUser(user), HttpStatus.OK);
    }

    @GetMapping(value = "/get/overall-info")
    public ResponseEntity<DriversWithStatuses> collectInfoAboutDrivers() {
        return new ResponseEntity<>(workloadDAO.getAllDriversSortedByTheirStatuses(), HttpStatus.OK);
    }
}
