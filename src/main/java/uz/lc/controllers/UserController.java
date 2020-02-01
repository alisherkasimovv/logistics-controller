package uz.lc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.lc.collections.UserAndMessage;
import uz.lc.db.dao.interfaces.UserDAO;
import uz.lc.db.entities.User;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private UserDAO dao;

    @Autowired
    public UserController(UserDAO dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(dao.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return new ResponseEntity<>(dao.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        dao.deleteById(id);
        return new ResponseEntity<>(dao.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<UserAndMessage> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(dao.saveUser(user), HttpStatus.OK);
    }

}
