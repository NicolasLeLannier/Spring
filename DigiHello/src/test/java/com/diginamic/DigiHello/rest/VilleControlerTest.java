/**
 *
 */
package com.diginamic.DigiHello.rest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;
import com.diginamic.DigiHello.repository.DepartementRepository;
import com.diginamic.DigiHello.repository.VilleRepository;

/**
 * @author Nicolas LE LANNIER
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VilleControlerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	DepartementRepository departementRepository;
	@MockBean
	VilleRepository villeRepository;

	@Test
	public void testGetAllVille() throws Exception {
		Ville v1 = new Ville("Paris", 2133111, new Departement("75", "Paris"));
		Ville v2 = new Ville("Marseille", 873076, new Departement("13", "Bouches-du-Rhône"));
		Ville v3 = new Ville("Lyon", 522250, new Departement("69", "Rhône"));
		Ville v4 = new Ville("Tours", 150000, new Departement("37", "Indre-et-Loire"));
		when(villeRepository.findAll()).thenReturn(List.of(v1, v2, v3, v4));
		
		// Test le webservice
		this.mockMvc.perform(MockMvcRequestBuilders.get("/villes")).andDo(print())
		 .andExpect(status().isOk())
		 .andExpect(content().string(containsString("Marseille")));
	}
}
