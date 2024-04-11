/**
 *
 */
package com.diginamic.DigiHello.repository;

import org.springframework.data.repository.CrudRepository;

import com.diginamic.DigiHello.model.Departement;

/** 
 * @author Nicolas LE LANNIER
 */
public interface DepartementRepository extends CrudRepository<Departement, Long> {

	Departement findByNom(String nom);
	Departement findByNumero(String code);

}
