/**
 *
 */
package com.diginamic.DigiHello.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.diginamic.DigiHello.exception.FunctionalException;
import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;
import com.diginamic.DigiHello.repository.VilleRepository;

/**
 * @author Nicolas LE LANNIER
 */
@SpringBootTest
@ActiveProfiles("test")
public class VilleServiceTest {

	@Autowired
	VilleService villeService;
	@Autowired
	VilleRepository villeRepository;

	@Test
	void extraireToutes() {
		Iterable<Ville> villes = villeService.extractVilles();
		assertTrue(villes.iterator().hasNext());
	}

	@Test
	public void testInsertVille() {		
		Assertions.assertDoesNotThrow(() -> villeService.insertVille(new Ville("Arbas", 279, new Departement("32", "Gers"))));
		Assertions.assertThrows(FunctionalException.class, () -> villeService.insertVille(new Ville("Arbas", 279, new Departement("31", "Haute-Garonne"))));
	}

}
