
<!DOCTYPE html>
<html>
	<head>
		<meta charset=\"utf-8\" />
		<title>Test</title>
	</head>
	<body>
		<%@ include file="menu.jsp" %>
		 
		<%-- 
		 #1ère methode: qui consiste a insérer directement du java dans nos pages jsp
		
			<p>Bonjour
			    <%
				    String name = (String) request.getAttribute("name"); // on get la var depuis le servelet.
				    out.println(name);
			    %>
			</p>
			
		--%>
		
		
 		 <p>Bonjour ${ !empty name ? name : '' }</p> <%-- //2ème methode: introduction des EL --%>
         <p>${ noms[2] }</p>
         
         
<!--          Manipulation d'objet beans -->
         <p>Bonjour ${ auteur.prenom } ${ auteur.nom }</p>
         <p>${ auteur.actif ? 'Vous êtes très actif !' : 'Vous êtes inactif !' }</p>
		
		
		<p>
		    <%
		        String heure = (String) request.getAttribute("heure"); // on accede a la var 'heure' (request.getAttribute) de TestServelet.
		        if (heure.equals("jour")) {
		            out.println("Bonjour"); 
		        }
		        else {
		            out.println("Bonsoir");
		        }
		    %>
		</p>
		
		<p>
			<c:out value = "ceci est un test JSTL" />
		</p>
		
		<p>
		    <%
		        for (int i = 0 ; i < 3 ; i++) {
		            out.println("Une nouvelle ligne !<br />");
		        }
		    %>
		</p>
		
<!-- 		partie: JSTL -->
		<c:if test="${ 50 > 10 }" var="variable">
    		C'est vrai !
		</c:if>
		
		<c:forEach var="i" begin="0" end="5" step="1">
    		<p>Un message n°<c:out value="${ i }" /> !</p>
		</c:forEach>
		
		<c:choose>
		    <c:when test="${ variable }">Du texte</c:when>
		    <c:when test="${ autreVariable }">Du texte</c:when>
		    <c:when test="${ encoreUneAutreVariable }">Du texte</c:when>
		    <c:otherwise></c:otherwise>
		</c:choose>
		
		
<!-- 		gestion de formulaires -->

        <c:if test="${ !empty form.resultat }"> <p><c:out value="${ form.resultat }" /></p> </c:if>
        <form method="post" action="bonjour">
        
        	<p>
				<label for="login">login : </label>
            	<input type="text" name="login" id="login" />			
            </p>
            
            <p>
				<label for="pass"> mot de pass : </label>
            	<input type="password" name="pass" id="pass" />			
            </p>
            
            <input type="submit" />
            
        </form>
        
        
       <%--  Gestion des fichiers
        <c:if test="${ !empty fichier }"><p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p></c:if>
        <form method="post" action="bonjour" enctype="multipart/form-data">
	        <p>
	            <label for="description">Description du fichier : </label>
	            <input type="text" name="description" id="description" />
	        </p>
	        <p>
	            <label for="fichier">Fichier à envoyer : </label>
	            <input type="file" name="fichier" id="fichier" />
	        </p>
        
        	<input type="submit" />
   	    </form> --%>
    
    	
    	<c:if test="${ !empty sessionScope.prenom && !empty sessionScope.nom }">
      		  <p>Vous êtes ${ sessionScope.prenom } ${ sessionScope.nom } !</p>
    	</c:if>
	    <form method="post" action="bonjour">
	        <p>
	            <label for="nom">Nom : </label>
	            <input type="text" name="nom" id="nom" />
	        </p>
	        <p>
	            <label for="prenom">Prénom : </label>
	            <input type="text" name="prenom" id="prenom" />
	        </p>
	        
	        <input type="submit" />
	    </form>
	   <!--  
	   		 Partie 5)
	    	 connexion et manip de bdd
	     -->
	     <form method="post" action="bonjour">
	        <p>
	            <label for="nom">Nom : </label>
	            <input type="text" name="nom" id="nom" />
	        </p>
	        <p>
	            <label for="prenom">Prénom : </label>
	            <input type="text" name="prenom" id="prenom" />
	        </p>
	        
        	<input type="submit" />
   		 </form>
    
	     <ul>
	        <c:forEach var="utilisateur" items="${ utilisateurs }">
	            <li><c:out value="${ utilisateur.prenom }" /> <c:out value="${ utilisateur.nom }" /></li>
	        </c:forEach>
	     </ul>    
        
	</body>
</html>