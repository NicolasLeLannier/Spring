package com.diginamic.DigiHello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;
import com.diginamic.DigiHello.repository.VilleRepository;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    private VilleRepository villeRepository;

    @Autowired
    public VilleControleur(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }
    
	@PostConstruct
	public void init() {
		//first time we create some towns because db is empty...
		insertVille(new Ville("Paris",2133111,new Departement("75","Paris")));
		insertVille(new Ville("Marseille", 873076,new Departement("13","Bouches-du-Rhône")));
		insertVille(new Ville("Lyon", 522250,new Departement("69","Rhône")));
		insertVille(new Ville("Tours", 150000,new Departement("37","Indre-et-Loire")));
		insertVille(new Ville("La Membrolle-sur-Choisille", 1000,new Departement("37","Indre-et-Loire")));
		insertVille(new Ville("Saint-Cyr-sur-Loire", 50000,new Departement("37","Indre-et-Loire")));
	}

    @GetMapping
    public List<Ville> getListeVilles() {
        return (List<Ville>) villeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
        Ville ville = villeRepository.findById(id).orElse(null);
        if (ville != null) {
            return new ResponseEntity<>(ville, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<List<Ville>> getVillesByNom(@PathVariable String nom) {
        List<Ville> villes = villeRepository.findByNomStartingWith(nom);
        if (!villes.isEmpty()) {
            return new ResponseEntity<>(villes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    // ---------------------------------------------------  Repository Methods -----------------------------------
    @GetMapping("/nom-commencant-par/{nom}")
    public List<Ville> getByNomStartingWith(@PathVariable String nom) {
        return villeRepository.findByNomStartingWith(nom);
    }

    @GetMapping("/population-superieure-a/{minPopulation}")
    public List<Ville> getByPopulationGreaterThan(@PathVariable int minPopulation) {
        return villeRepository.findByNbHabitantGreaterThan(minPopulation);
    }

    @GetMapping("/population-entre/{minPopulation}/{maxPopulation}")
    public List<Ville> getByPopulationBetween(@PathVariable int minPopulation, @PathVariable int maxPopulation) {
        return villeRepository.findByNbHabitantBetween(minPopulation, maxPopulation);
    }

    @GetMapping("/population-departement-superieure-a/{departementId}/{minPopulation}")
    public List<Ville> getByDepartementAndPopulationGreaterThan(@PathVariable Long departementId, @PathVariable int minPopulation) {
        return villeRepository.findByDepartementIdAndNbHabitantGreaterThan(departementId, minPopulation);
    }

    @GetMapping("/population-departement-entre/{departementId}/{minPopulation}/{maxPopulation}")
    public List<Ville> getByDepartementAndPopulationBetween(@PathVariable Long departementId, @PathVariable int minPopulation, @PathVariable int maxPopulation) {
        return villeRepository.findByDepartementIdAndNbHabitantBetween(departementId, minPopulation, maxPopulation);
    }

    @GetMapping("/plus-peuples/{departementId}/{size}")
    public List<Ville> getMostPopulousByDepartement(@PathVariable Long departementId, @PathVariable int size) {
    	return villeRepository.findByDepartementIdOrderByNbHabitantDesc(departementId, Pageable.ofSize(size)).getContent();
    }
    


    @PostMapping
    public ResponseEntity<Ville> insertVille(@RequestBody Ville ville) {
    	Ville result = villeRepository.findByNom(ville.getNom());
    	if(result == null) {
    		Ville savedVille = villeRepository.save(ville);
            return new ResponseEntity<>(savedVille, HttpStatus.CREATED);
    	} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ville> modifierVille(@PathVariable int id, @RequestBody Ville villeModifiee) {
        Ville existingVille = villeRepository.findById(id).orElse(null);
        if (existingVille != null) {
            villeModifiee.setId(id); // Ensure ID consistency
            Ville updatedVille = villeRepository.save(villeModifiee);
            return new ResponseEntity<>(updatedVille, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerVille(@PathVariable int id) {
        Ville existingVille = villeRepository.findById(id).orElse(null);
        if (existingVille != null) {
            villeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
