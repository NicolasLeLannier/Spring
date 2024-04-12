/**
 *
 */
package com.diginamic.DigiHello.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;
import com.diginamic.DigiHello.service.VilleService;

/** 
 * @author Nicolas LE LANNIER
 */
@SpringBootTest
@ActiveProfiles("test")
public class VilleRepositoryTest {

	@Autowired
	VilleService villeService;
	@Autowired
	@MockBean
	VilleRepository villeRepository;
	
	
//	@Test
//	public void testFindByNomStartingWith() {
//		List<Ville> v = villeRepository.findByNomStartingWith("Par");
//		assertEquals(v.size(), 1);
//		assertNotNull(v.get(0));
//		assertEquals("Paris", v.get(0).getNom());
//	}
	
	@Test
	public void testFindByNom() {
		Mockito.when(villeRepository.findByNom("Paris")).thenReturn(new Ville("Paris", 142000, new Departement("75", "Paris")));
		Ville ville = villeRepository.findByNom("Paris");
		assertNotNull(ville);
		assertEquals("Paris", ville.getNom());
	}
	
}
