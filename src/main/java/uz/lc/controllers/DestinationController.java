package uz.lc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lc.db.dao.interfaces.DestinationDAO;
import uz.lc.db.entities.Destination;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

@RestController
@RequestMapping("/destinations")
@CrossOrigin("http://localhost:3000")
public class DestinationController {

    private DestinationDAO destinationDAO;

    public DestinationController(DestinationDAO destinationDAO) {
        this.destinationDAO = destinationDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Destination>> getAllDestinations() {
        return new ResponseEntity<>(destinationDAO.getAllDestinations(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Destination> getOneDestinations(@PathVariable Integer id) {
        return new ResponseEntity<>(destinationDAO.getOneDestination(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ReturningObjectAndMessage> saveDestination(@RequestBody Destination destination) {
        return new ResponseEntity<>(destinationDAO.saveDestination(destination), HttpStatus.OK);
    }

}
