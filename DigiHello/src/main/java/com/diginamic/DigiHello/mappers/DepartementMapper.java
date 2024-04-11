/**
 *
 */
package com.diginamic.DigiHello.mappers;

import org.springframework.stereotype.Component;

import com.diginamic.DigiHello.dto.DepartementDTO;
import com.diginamic.DigiHello.model.Departement;

/**
 * @author Nicolas LE LANNIER
 */
@Component
public class DepartementMapper {

	public DepartementDTO toDto(Departement departement) {
		DepartementDTO dto = new DepartementDTO();
		dto.setNumero(departement.getNumero());
		dto.setNom(departement.getNom());
		dto.setNbHabitants(departement.getVilles().size());
		return dto;
	}

	public Departement toBean(DepartementDTO departement) {
		Departement bean = new Departement();
		bean.setNom(departement.getNom());
		bean.setNumero(departement.getNumero());
		// etc.
		return bean;
	}
}
