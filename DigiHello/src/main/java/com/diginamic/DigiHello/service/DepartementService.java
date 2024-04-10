package com.diginamic.DigiHello.service;

import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @PersistenceContext
    private EntityManager em;

    public List<Departement> getAllDepartements() {
        TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d", Departement.class);
        return query.getResultList();
    }
    
    public Departement getDepartementById(Long id) {
        return em.find(Departement.class, id);
    }

    public List<Departement> insertDepartement(Departement departement) {
		em.persist(departement);
		return getAllDepartements();
	}
	
	public List<Departement> modifierDepartement(Long idVille, Departement departementModifiee) {
		System.out.println(departementModifiee);
		Departement departement = getDepartementById(idVille);
		if(departement != null) {
			departement.setNom(departementModifiee.getNom());
			departement.setNumero(departementModifiee.getNumero());
			em.merge(departement);
		}
		return getAllDepartements();
	}
	
	public List<Departement> supprimerDepartement(Long idVille) {
		Departement departement = getDepartementById(idVille);
		if(departement != null) {
			em.remove(departement);
		}
		return getAllDepartements();
	}



	public List<Ville> getNBiggestCities(Long id, int nb) {
        // Requête SQL pour sélectionner les n plus grandes villes d'un département
        String sql = "SELECT v FROM Ville v WHERE v.departement.id = :id ORDER BY v.nbHabitant DESC";
        TypedQuery<Ville> query = em.createQuery(sql, Ville.class);
        query.setParameter("id", id);
        query.setMaxResults(nb);
        return query.getResultList();
    }

    public List<Ville> getCitiesByPopulationRange(Long num, int minPopulation, int maxPopulation) {
        // Requête SQL pour sélectionner les villes ayant une population comprise entre un minimum et un maximum pour un département donné
        String sql = "SELECT v FROM Ville v INNER JOIN departement d ON d.id = v.departement_id WHERE d.numero = :num AND v.nb_Habitant BETWEEN :minPopulation AND :maxPopulation";
        TypedQuery<Ville> query = em.createQuery(sql, Ville.class);
        query.setParameter("num", num);
        query.setParameter("minPopulation", minPopulation);
        query.setParameter("maxPopulation", maxPopulation);
        return query.getResultList();
    }
}
