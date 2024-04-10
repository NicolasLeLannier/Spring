package com.diginamic.DigiHello.controleurs;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.DigiHello.model.Ville;
import com.diginamic.DigiHello.service.VilleService;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    private VilleService villeService;

    public VilleControleur(VilleService villeService) {
        this.villeService = villeService;
    }

    @GetMapping
    public List<Ville> getListeVilles() {
        return villeService.extractVilles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
        Ville ville = villeService.extractVille(id);
        if (ville != null) {
            return new ResponseEntity<>(ville, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<Ville> getVilleByName(@PathVariable String nom) {
        Ville ville = villeService.extractVille(nom);
        if (ville != null) {
            return new ResponseEntity<>(ville, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<List<Ville>> insertVille(@RequestBody Ville ville) {
        List<Ville> villes = villeService.insertVille(ville);
        return new ResponseEntity<>(villes, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Ville>> modifierVille(@PathVariable int id, @RequestBody Ville villeModifiee) {
        List<Ville> villes = villeService.modifierVille(id, villeModifiee);
        if (villes != null) {
            return new ResponseEntity<>(villes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Ville>> supprimerVille(@PathVariable int id) {
        List<Ville> villes = villeService.supprimerVille(id);
        if (villes != null) {
            return new ResponseEntity<>(villes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
