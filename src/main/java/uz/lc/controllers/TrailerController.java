package uz.lc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.lc.db.dao.interfaces.TrailerDAO;
import uz.lc.db.entities.Trailer;

import java.util.List;

@RestController
@RequestMapping("/trailers")
public class TrailerController {

    private TrailerDAO trailerDAO;

    public TrailerController(TrailerDAO trailerDAO) {
        this.trailerDAO = trailerDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Trailer>> getAllNonDeletedTrailers() {
        return new ResponseEntity<>(trailerDAO.getAllNonDeletedTrailers(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/deleted")
    public ResponseEntity<List<Trailer>> getAllDeletedTrailers() {
        return new ResponseEntity<>(trailerDAO.getAllTrailers(), HttpStatus.OK);
    }

}
