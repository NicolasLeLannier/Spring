package com.diginamic.DigiHello.controleurs;

import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;
import com.diginamic.DigiHello.service.DepartementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

    private final DepartementService departementService;

    public DepartementControleur(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    public ResponseEntity<List<Departement>> getAllDepartements() {
        List<Departement> departements = departementService.getAllDepartements();
        return new ResponseEntity<>(departements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable Long id) {
        Departement departement = departementService.getDepartementById(id);
        if (departement != null) {
            return new ResponseEntity<>(departement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<List<Departement>> insertDepartement(@RequestBody Departement ville) {
        List<Departement> departement = departementService.insertDepartement(ville);
        return new ResponseEntity<>(departement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Departement>> modifierDepartement(@PathVariable Long id, @RequestBody Departement villeModifiee) {
        List<Departement> departement = departementService.modifierDepartement(id, villeModifiee);
        if (departement != null) {
            return new ResponseEntity<>(departement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Departement>> supprimerDepartement(@PathVariable Long id) {
        List<Departement> departement = departementService.supprimerDepartement(id);
        if (departement != null) {
            return new ResponseEntity<>(departement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    


    @GetMapping("/{num}/{nb}")
    public ResponseEntity<List<Ville>> getNBiggestCities(@PathVariable Long num, @PathVariable int n) {
        List<Ville> villes = departementService.getNBiggestCities(num, n);
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }

    @GetMapping("/{num}/{minPopulation}/{maxPopulation}")
    public ResponseEntity<List<Ville>> getCitiesByPopulationRange(@PathVariable Long num, @PathVariable int minPopulation, @PathVariable int maxPopulation) {
        List<Ville> villes = departementService.getCitiesByPopulationRange(num, minPopulation, maxPopulation);
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }
}
