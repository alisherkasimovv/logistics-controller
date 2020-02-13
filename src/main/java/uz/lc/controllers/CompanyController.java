package uz.lc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lc.db.dao.interfaces.CompanyDAO;
import uz.lc.db.dao.interfaces.DestinationDAO;
import uz.lc.db.entities.Company;
import uz.lc.db.entities.Destination;
import uz.lc.dto.CompanyWithDestinations;
import uz.lc.dto.ReturningObjectAndMessage;

import java.util.List;

@RestController
@RequestMapping("/companies")
@CrossOrigin("http://localhost:3000")
public class CompanyController {

    private CompanyDAO companyDAO;

    public CompanyController(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Company>> getAllCompany() {
        return new ResponseEntity<>(companyDAO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/with-destinations")
    public ResponseEntity<List<CompanyWithDestinations>> getAllCompaniesWithDestinations() {
        return new ResponseEntity<>(companyDAO.getAllCompaniesWithDestinations(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable int id) {
        return new ResponseEntity<>(companyDAO.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}/with-destinations")
    public ResponseEntity<CompanyWithDestinations> getCompanyWithDestinations(@PathVariable Integer id) {
        return new ResponseEntity<>(companyDAO.getOneCompanyWithDestinations(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ReturningObjectAndMessage> saveCompany(@RequestBody Company company) {
        return new ResponseEntity<>(companyDAO.saveCompany(company), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public HttpStatus deleteCompany(@PathVariable int id) {
        companyDAO.deleteById(id);
        return HttpStatus.OK;
    }

}


