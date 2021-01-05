package bj.octest1.beans;

/**
 * class objet bean decrivant un author
 * 
 * @author koudousibouraima
 *
 */
public class Author {
	
	 private String nom;
	 private String prenom;
	 private boolean actif;
	 
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}

}
