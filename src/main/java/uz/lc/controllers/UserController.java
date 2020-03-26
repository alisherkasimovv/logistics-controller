package uz.lc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lc.db.dao.UserDAOImpl;
import uz.lc.db.dao.interfaces.DriverWorkloadDAO;
import uz.lc.db.dao.interfaces.UserDAO;
import uz.lc.db.entities.User;
import uz.lc.db.enums.UserType;
import uz.lc.dto.DriversWithStatuses;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private UserDAO dao;
    private DriverWorkloadDAO workloadDAO;

    public UserController(UserDAOImpl dao, DriverWorkloadDAO workloadDAO) {
        this.dao = dao;
        this.workloadDAO = workloadDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(dao.getAllNonDeleted(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/deleted")
    public ResponseEntity<List<User>> getAllDeletedUsers() {
        return new ResponseEntity<>(dao.getAllDeleted(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/id={id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return new ResponseEntity<>(dao.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get/username={username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return new ResponseEntity<>(dao.getByUsername(username), HttpStatus.OK);
    }

    @GetMapping(value = "/get/type={type}")
    public ResponseEntity<List<User>> getUsersByType(@PathVariable String type) {
        try {
            return new ResponseEntity<>(dao.getAllUsersByType(UserType.valueOf(type.toUpperCase())), HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        dao.deleteById(id);
        return new ResponseEntity<>(dao.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ReturningObjectAndMessage> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(dao.saveUser(user), HttpStatus.OK);
    }

    @GetMapping(value = "/get/overall-drivers-info")
    public ResponseEntity<DriversWithStatuses> collectInfoAboutDrivers() {
        return new ResponseEntity<>(workloadDAO.getAllDriversSortedByTheirStatuses(), HttpStatus.OK);
    }

}
