/**
 *
 */
package com.diginamic.DigiHello.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/** 
 * @author Nicolas LE LANNIER
 */
@Entity
public class Ville {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private int nbHabitant;
	
	/** Constructeur
	 * 
	 */
	public Ville() {
	}
	
	/** Constructeur
	 * @param nom
	 * @param nbHabitant
	 */
	public Ville(String nom, int nbHabitant) {
		super();
		this.nom = nom;
		this.nbHabitant = nbHabitant;
	}
	
	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/** Getter
	 * @return the nbHabitant
	 */
	public int getNbHabitant() {
		return nbHabitant;
	}
	/** Setter
	 * @param nbHabitant the nbHabitant to set
	 */
	public void setNbHabitant(int nbHabitant) {
		this.nbHabitant = nbHabitant;
	}
	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}	
	
}
