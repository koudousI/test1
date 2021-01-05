package bj.octest1.dao;


import java.util.List;

import bj.octest1.beans.Utilisateur;

/**
 * interface contenant les methodes ajouter() et lister().
 * 
 * @author koudousibouraima
 *
 */
public interface UtilisateurDao {
	
    void ajouter( Utilisateur utilisateur );
    
    List<Utilisateur> lister();
}