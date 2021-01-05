package bj.octest1.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bj.octest1.beans.Author;
import bj.octest1.forms.ConnectionForm;

import bj.octest1.bdd.Noms;
import bj.octest1.beans.Utilisateur;
import bj.octest1.dao.*;


/**
 * tout les Servlet héritent de la classe HttpServlet
 * 
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final int TAILLE_TAMPON = 10240;
	public static final String CHEMIN_FICHIERS = "/Users/koudousibouraima/demo2/"; // A changer

	
	 private UtilisateurDao utilisateurDao;
	
	
	 public void init() throws ServletException {
	        DaoFactory daoFactory = DaoFactory.getInstance();
	        this.utilisateurDao = daoFactory.getUtilisateurDao();
	    }
	 
	 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		/*
		 * // affichage de bonjour sur une fenetre.
		 * 
		 * response.setContentType("text/html"); // on renvoie du html
		 * response.setCharacterEncoding("UTF-8"); // on encode sous forme de utf-8
		 * 
		 * PrintWriter out = response.getWriter(); // sortie standart JEE
		 * 
		 * // affichage à l'écrande bonjour (html diretement écrit ci dessous)
		 * 
		 * out.println("<!DOCTYPE html>"); out.println("<html>"); out.println("<head>");
		 * out.println("<meta charset=\"utf-8\" />");
		 * out.println("<title>Test</title>"); out.println("</head>");
		 * out.println("<body>"); out.println("<p>Bonjour my man!</p>");
		 * out.println("</body>"); out.println("</html>");
		 */

		/*
		 * affichage de bonjour sur une fenetre. on renvoie du html affichage à
		 * l'écrande bonjour (appel d'une page jsp nomé bonjour.jsp)
		 */

		request.setAttribute("heure", "Soir"); // on creer une var 'heure' (request.setAttribute) accessible depuis jsp
												// et ayant pour valeur "jour"

		String name = request.getParameter("name"); // mauvaise pratique: recup du param passé à l'url // bonne
													// peratique: ceer un objet metier (voir ex sur la connection)
		request.setAttribute("name", name); // on creer une var accessible depuis jsp

		String[] noms = { "Koudous", "Abdou", "Ibouraima" }; // tab de noms
		request.setAttribute("noms", noms);

		// Manipulations d'objets beans
		Author auteur = new Author();
		auteur.setPrenom("Koudous");
		auteur.setNom("Ibouraima");
		auteur.setActif(true);
		request.setAttribute("auteur", auteur); // on creer une var (objet) accessible depuis jsp

		
		/**
		// Manipulation de cookies: recuperation de cookies
		 Cookie[] cookies = request.getCookies(); //recup ds un tab ts les cookies pour ce site
	        if (cookies != null) {
	            for (Cookie cookie : cookies) { // on iter sur les cookies
	                if (cookie.getName().equals("prenom")) {
	                    request.setAttribute("prenom", cookie.getValue());
	                }
	            }
	        }
		*/
		
		/*
		 * Partie 5)
		 * conexion et manip de la bdd
		 */
		Noms tableNoms = new Noms();
        request.setAttribute("utilisateurs", tableNoms.recupererUtilisateurs()); // recup les occ de la bdd
		
        //manipulation de la Dao
        request.setAttribute("utilisateurs", utilisateurDao.lister());

        
		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		/*
		 * bonne pratique pour ne par ecrire les teste (if, cond) 
		 * directement dans nos servlets et de mieux gerer les jsp
		 * en créant un objet metier.
		 */
		ConnectionForm form =new ConnectionForm();
		form.verifierIdentifiants(request); 
		request.setAttribute("form", form); // on recup l'objet form et donc resultat
        
		/*
		 * Manipulation de Sessions
		 */
		 String nom = request.getParameter("nom");
	     String prenom = request.getParameter("prenom");
	        
	     HttpSession session = request.getSession();

	     session.setAttribute("nom", nom);
	     session.setAttribute("prenom", prenom);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);

		
		/*
		 * // Gestion de cookies: creation de cookies 
		
		    String nom = request.getParameter("nom");
        	String prenom = request.getParameter("prenom");
        
        	Cookie cookie = new Cookie("prenom", prenom);
        	cookie.setMaxAge(60 * 60 * 24 * 30); // age d'expiretion
        	response.addCookie(cookie);
		 */
		
		
		/*
		 * Gestion des fichiers
		 
		 
		// On récupère le champ description comme d'habitude
        String description = request.getParameter("description");
        request.setAttribute("description", description );

        // On récupère le champ du fichier
        Part part = request.getPart("fichier");
            
        // On vérifie qu'on a bien reçu un fichier
        String nomFichier = getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()) {
            String nomChamp = part.getName();
            // Corrige un bug du fonctionnement d'Internet Explorer
             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

            request.setAttribute(nomChamp, nomFichier);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
		
	}

	
	
	
	*//**
	 * 
	 * @param part
	 * @param nomFichier
	 * @param chemin
	 * @throws IOException
	 *//*
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
	
	*//**
	 * 
	 * @param part
	 * @return
	 *//*
	 private static String getNomFichier( Part part ) {
	        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	            if ( contentDisposition.trim().startsWith( "filename" ) ) {
	                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
	            }
	        }
	        return null;
	    }   
	 */
		
		/*
		 * Partie 5)
		 * Connexion et manip de la bdd
		 * 
		 */
		Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getParameter("nom"));
        utilisateur.setPrenom(request.getParameter("prenom"));
        
        Noms tableNoms = new Noms();
        tableNoms.ajouterUtilisateur(utilisateur); // ajout a la bdd
        
        /*
         * Manip de la Dao
         */
        //Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getParameter("nom"));
        utilisateur.setPrenom(request.getParameter("prenom"));
        
        utilisateurDao.ajouter(utilisateur);
        
        request.setAttribute("utilisateurs", utilisateurDao.lister());
        
        request.setAttribute("utilisateurs", tableNoms.recupererUtilisateurs()); // recup les occ de la bdd
        
	} 
}
