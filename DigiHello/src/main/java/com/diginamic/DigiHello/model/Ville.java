/**
 *
 */
package com.diginamic.DigiHello.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

/** 
 * @author Nicolas LE LANNIER
 */
@Entity
public class Ville {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String nom;
	@Column(nullable = false)
	private int nbHabitant;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "departement_id", nullable = false)
	@NotNull
    private Departement departement;
	
	/** Constructeur
	 * 
	 */
	public Ville() {
	}
	
	/** Constructeur
	 * @param nom
	 * @param nbHabitant
	 */
	public Ville(String nom, int nbHabitant, Departement departement) {
		super();
		this.nom = nom;
		this.nbHabitant = nbHabitant;
		this.departement = departement;
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

	/** Getter
	 * @return the departement
	 */
	public Departement getDepartement() {
		return departement;
	}

	/** Setter
	 * @param departement the departement to set
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}	
	
}
