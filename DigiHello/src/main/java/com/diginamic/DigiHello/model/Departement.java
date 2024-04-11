/**
 *
 */
package com.diginamic.DigiHello.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/** 
 * @author Nicolas LE LANNIER
 */
@Entity
public class Departement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numero;
	private String nom;
	
	@OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
	@JsonIgnore		// Empêche la recursion infini dans le JSON
    private List<Ville> villes = new ArrayList<>();


	/** Constructeur
	 * @param numDepart
	 * @param nom
	 */
	public Departement(String numero, String nom) {
		super();
		this.numero = numero;
		this.nom = nom;
	}

	/** Constructeur
	 * 
	 */
	public Departement() {
		super();
	}

	/** Getter
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the numDepart
	 */
	public String getNumero() {
		return numero;
	}

	/** Setter
	 * @param numDepart the numDepart to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/** Getter
	 * @return the villes
	 */
	public List<Ville> getVilles() {
		return villes;
	}

	/** Setter
	 * @param villes the villes to set
	 */
	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}
	
}
