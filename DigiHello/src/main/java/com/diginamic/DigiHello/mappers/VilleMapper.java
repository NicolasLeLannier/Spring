/**
 *
 */
package com.diginamic.DigiHello.mappers;

import org.springframework.stereotype.Component;

import com.diginamic.DigiHello.dto.VilleDTO;
import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;

/** 
 * @author Nicolas LE LANNIER
 */
@Component
public class VilleMapper {

	public VilleDTO toDto(Ville ville) {
		VilleDTO dto = new VilleDTO();
		dto.setNbHabitant(ville.getNbHabitant());
		dto.setNom(ville.getNom());
		dto.setCodeDepartement(ville.getDepartement().getNumero());
        dto.setNomDepartement(ville.getDepartement().getNom());
		return dto;
	}

	public Ville toBean(VilleDTO villeDto) {
		Ville bean = new Ville();
		bean.setNbHabitant(villeDto.getNbHabitant());
		bean.setNom(villeDto.getNom());
		
        Departement departement = new Departement();
        departement.setNumero(villeDto.getCodeDepartement());
        departement.setNom(villeDto.getNomDepartement());
        bean.setDepartement(departement);
		return bean;
	}
	
}
