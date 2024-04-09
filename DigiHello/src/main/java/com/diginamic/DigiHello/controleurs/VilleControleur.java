package com.diginamic.DigiHello.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.DigiHello.model.Ville;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    private List<Ville> villes;

    public VilleControleur() {
        this.villes = new ArrayList<>();
        // Ajouter des villes de base pour l'exemple
        villes.add(new Ville("Paris", 2200000));
        villes.add(new Ville("Marseille", 850000));
        villes.add(new Ville("Lyon", 500000));
        villes.add(new Ville("Tours", 150000));
    }

    @GetMapping
    public List<Ville> getListeVilles() {
        return villes;
    }

    @PostMapping
    public String ajouterVille(@RequestBody Ville nouvelleVille) {
        for (Ville ville : villes) {
            if (ville.getNom().equals(nouvelleVille.getNom())) {
                return "La ville existe déjà";
            }
        }
        
        System.out.println(nouvelleVille.getNom());
        villes.add(nouvelleVille);
        return "Ville insérée avec succès";
    }
}

