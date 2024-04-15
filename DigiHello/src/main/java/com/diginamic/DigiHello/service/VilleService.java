/**
 *
 */
package com.diginamic.DigiHello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.diginamic.DigiHello.exception.FunctionalException;
import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;
import com.diginamic.DigiHello.repository.DepartementRepository;
import com.diginamic.DigiHello.repository.VilleRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * @author Nicolas LE LANNIER
 */
@Service
@Transactional
public class VilleService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private DepartementRepository departementRepository;

	@Autowired
	private VilleRepository villeRepository;

	public List<Ville> extractVilles() {
		TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v", Ville.class);
		return query.getResultList();
	}

	public Ville extractVille(int idVille) {
		return em.find(Ville.class, idVille);
	}

	public Ville extractVille(String nom) {
		TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v WHERE v.nom = " + nom + "", Ville.class);
		return query.getSingleResult();
	}

	public List<Ville> insertVille(Ville ville) throws FunctionalException {
	    if (villeRepository.existsByNom(ville.getNom())) {
	        throw new FunctionalException("Ville already exists");
	    }
	    em.persist(ville);
	    return extractVilles();
	}


	public List<Ville> modifierVille(int idVille, Ville villeModifiee) {
		System.out.println(villeModifiee);
		Ville ville = extractVille(idVille);
		if (ville != null) {
			ville.setNom(villeModifiee.getNom());
			ville.setNbHabitant(villeModifiee.getNbHabitant());
			em.merge(ville);
		}
		return extractVilles();
	}

	public List<Ville> supprimerVille(int idVille) {
		Ville ville = extractVille(idVille);
		if (ville != null) {
			em.remove(ville);
		}
		return extractVilles();
	}

	public Ville saveVilleWithDepartement(Ville ville) {
		Departement departement = ville.getDepartement();
		departement = departementRepository.save(departement); // Save the departement first
		ville.setDepartement(departement); // Set the saved departement to the ville
		return villeRepository.save(ville); // Then save the ville
	}
	
	
	@Async
	public void exportToCsvOnDisk() {
		for(int i = 0; i < 10000000; i++)
		System.out.println("Launched by get : " + i);
	}
}
