package bj.octest1.forms;

import javax.servlet.http.HttpServletRequest;

/**
 * Objet metier permetant de se connecter
 * 
 * @author koudousibouraima
 *
 */
public class ConnectionForm {
	
	private String resultat;
	
	/**
	 *  check if you tape good informations
	 * @param request
	 */
	public void verifierIdentifiants(HttpServletRequest request) {
		
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		
		if (pass.equals(login + "123")) {
			resultat = "Vous etes bien connecté !";
		}else {
			resultat = "Identifiants incorrects !";
		}
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	
}
