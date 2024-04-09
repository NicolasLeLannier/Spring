/**
 *
 */
package com.diginamic.DigiHello.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.DigiHello.model.Ville;

/** 
 * @author Nicolas LE LANNIER
 */
@RestController
@RequestMapping("/villes")
public class VilleControleur {

	@GetMapping
	public List<Ville> getListeVilles() {
        List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("Paris", 2200000));
        villes.add(new Ville("Marseille", 850000));
        villes.add(new Ville("Lyon", 500000));
        villes.add(new Ville("Tours", 150000));
        return villes;
    }
	
}
