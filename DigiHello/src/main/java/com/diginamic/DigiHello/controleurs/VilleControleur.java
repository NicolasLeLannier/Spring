package com.diginamic.DigiHello.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.DigiHello.model.Ville;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    private ArrayList<Ville> villes;

    public VilleControleur() {
        this.villes = new ArrayList<>();
        // Ajouter des villes de base pour l'exemple
        villes.add(new Ville("Paris", 2200000));
        villes.add(new Ville("Marseille", 850000));
        villes.add(new Ville("Lyon", 500000));
        villes.add(new Ville("Tours", 150000));
    }

    @GetMapping
    public ArrayList<Ville> getListeVilles() {
        return villes;
    }

    @PostMapping
    public String ajouterVille(@RequestBody Ville nouvelleVille) {   	
//    	Ville result = findTownByName(nouvelleVille.getNom());
//    	if(result == null) {
//    		villes.add(nouvelleVille);
//    	} else {
//    		return new ResponseEntity.badRequest().body("La ville existe déjà");
//    	}
    	
        for (Ville ville : villes) {
            if (ville.getNom().equals(nouvelleVille.getNom())) {
                return "La ville existe déjà";
            }
        }
        
//        System.out.println(nouvelleVille.getNom());
        villes.add(nouvelleVille);
        return "Ville insérée avec succès";
    }
    
	private Ville findTownByName(String name) {
		Ville result = this.villes.stream().filter(element -> name.equals(element.getNom())).findAny().orElse(null);
		return result;
	}
	
	
	@GetMapping("/{id}")
    public Ville getVilleById(@PathVariable int id) {
        for (Ville ville : villes) {
            if (ville.getId() == id) {
                return ville;
            }
        }
        return null;
    }
	
    @PostMapping("/{id}")
    public String modifierVille(@PathVariable int id, @RequestBody Ville villeModifiee) {
        for (Ville ville : villes) {
            if (ville.getId() == id) {
                ville.setNom(villeModifiee.getNom());
                ville.setNbHabitant(villeModifiee.getNbHabitant());
                return "Ville modifiée avec succès";
            }
        }
        return "Ville non trouvée";
    }

    @DeleteMapping("/{id}")
    public String supprimerVille(@PathVariable int id) {
        for (Ville ville : villes) {
            if (ville.getId() == id) {
                villes.remove(ville);
                return "Ville supprimée avec succès";
            }
        }
        return "Ville non trouvée";
    }
   
}

