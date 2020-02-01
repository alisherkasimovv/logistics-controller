package uz.lc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.lc.db.dao.interfaces.CompanyDAO;
import uz.lc.db.entities.Company;

import java.util.List;

@RestController
@RequestMapping("/companies")
@CrossOrigin("http://localhost:3000")
public class CompanyController {

    private CompanyDAO dao;

    @Autowired
    public CompanyController(CompanyDAO dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Company>> getAllCompany() {
        return new ResponseEntity<>(dao.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable int id) {
        return new ResponseEntity<>(dao.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public HttpStatus deleteCompany(@PathVariable int id) {
        dao.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/save")
    public HttpStatus saveUser(@RequestBody Company company) {
        dao.saveCompany(company);
        return HttpStatus.OK;
    }

}


