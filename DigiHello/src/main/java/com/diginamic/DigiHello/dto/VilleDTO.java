package com.diginamic.DigiHello.dto;

public class VilleDTO {
    private String nom;
    private int nbHabitant;
    private String codeDepartement;
    private String nomDepartement;

    /** Constructeur
	 * 
	 */
	public VilleDTO() {
		super();
	}

	/** Constructeur
	 * @param nom
	 * @param nbHabitant
	 * @param codeDepartement
	 * @param nomDepartement
	 */
	public VilleDTO(String nom, int nbHabitant, String codeDepartement, String nomDepartement) {
		super();
		this.nom = nom;
		this.nbHabitant = nbHabitant;
		this.codeDepartement = codeDepartement;
		this.nomDepartement = nomDepartement;
	}

	/** Getter
	 * @return the codeDepartement
	 */
	public String getCodeDepartement() {
		return codeDepartement;
	}

	/** Setter
	 * @param codeDepartement the codeDepartement to set
	 */
	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	/** Getter
	 * @return the nomDepartement
	 */
	public String getNomDepartement() {
		return nomDepartement;
	}

	/** Setter
	 * @param nomDepartement the nomDepartement to set
	 */
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
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
}
