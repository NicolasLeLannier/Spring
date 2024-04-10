/**
 *
 */
package com.diginamic.DigiHello.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.diginamic.DigiHello.model.Ville;
import java.util.List;


/** 
 * @author Nicolas LE LANNIER
 */
public interface VilleRepository extends CrudRepository<Ville, Integer> {
	
	// Recherche par nom
	Ville findByNom(String nom);

	// Recherche de toutes les villes dont le nom commence par une chaine de caractères données
    List<Ville> findByNomStartingWith(String nom);

    // Recherche de toutes les villes dont la population est supérieure à min (paramètre de type int)
    List<Ville> findByNbHabitantGreaterThan(int minPopulation);

    // Recherche de toutes les villes dont la population est comprise entre min et max
    List<Ville> findByNbHabitantBetween(int minPopulation, int maxPopulation);

    // Recherche de toutes les villes d’un département dont la population est supérieure à min
    List<Ville> findByDepartementIdAndNbHabitantGreaterThan(Long departementId, int minPopulation);

    // Recherche de toutes les villes d’un département dont la population est comprise entre min et max
    List<Ville> findByDepartementIdAndNbHabitantBetween(Long departementId, int minPopulation, int maxPopulation);

    // Recherche des n villes les plus peuplées d’un département donné (n est aussi un paramètre)
    Page<Ville> findByDepartementIdOrderByNbHabitantDesc(Long departementId, Pageable pageable);

}
