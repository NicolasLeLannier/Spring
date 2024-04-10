/**
 *
 */
package com.diginamic.DigiHello.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.diginamic.DigiHello.model.Ville;

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
	
	public List<Ville> insertVille(Ville ville) {
		em.persist(ville);
		return extractVilles();
	}
	
	public List<Ville> modifierVille(int idVille, Ville villeModifiee) {
		System.out.println(villeModifiee.getNom() + " | " + villeModifiee.getNbHabitant() + " | " + villeModifiee.getId() + " | ");
		Ville ville = extractVille(idVille);
		if(ville != null) {
			ville.setNom(villeModifiee.getNom());
			ville.setNbHabitant(villeModifiee.getNbHabitant());
			em.merge(ville);
		}
		return extractVilles();
	}
	
	public List<Ville> supprimerVille(int idVille) {
		Ville ville = extractVille(idVille);
		if(ville != null) {
			em.remove(ville);
		}
		return extractVilles();
	}
}
