package uz.lc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lc.collections.TruckAndMessage;
import uz.lc.db.dao.interfaces.TruckDAO;
import uz.lc.db.entities.Truck;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trucks")
@CrossOrigin("http://localhost:3000")
public class TruckController {
    private TruckDAO truckDAO;

    @Autowired
    public TruckController(TruckDAO truckDAO) {
        this.truckDAO=truckDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Truck>> getAllTrucks() {
        return new ResponseEntity<>(truckDAO.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Truck> getTruck(@PathVariable int id) {
        return new ResponseEntity<>(truckDAO.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public HttpStatus deleteTruck(@PathVariable int id) {
        truckDAO.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<TruckAndMessage> saveTruck(@Valid @RequestBody Truck truck) {
        return new ResponseEntity<>(truckDAO.saveTruck(truck), HttpStatus.OK);
    }
}
