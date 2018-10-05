<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../../entete.jsp"%>
<title>gestions des inscriptions</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExample08" aria-controls="navbarsExample08"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse justify-content-md-center"
			id="navbarsExample08">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur">
						Accueil Responsable </a></li>

				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/inscriptions">Gestion
						des inscriptions<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/resultats">Consultation
						des resultats</a></li>

			</ul>
		</div>
	</nav>
	<%@include file="../../debutBody.jsp"%>
	<div class="col col-lg-9">
		<br> <br>
		<h1>Modification d'un candidat</h1>
		<br>
		<c:if test="${sessionScope.messageValidation != null }">
			<h2 style="color: red;">${sessionScope.messageValidation}</h2>
			<br>
		</c:if>

		<div class="col col-lg-10 justify-content-lg-center offset-lg-1">
			<div id="afficherinfos">
				<div class="form-row">
					<label for="nom" class="col col-lg-4">Recherche par
						nom/mail </label> <input type="text"
						class="form-control col col-lg-7 offset-lg-1" name="nom" id="nom">
				</div>
				<div id="results"></div>
			</div>
		</div>

	</div>
	</div>
	</div>

	<script type="text/javascript">
	
jQuery("input[name='nom']").on("input", function() {
	var xhr; 
    try {  xhr = new ActiveXObject('Msxml2.XMLHTTP');   }
    catch (e) 
    {
        try {   xhr = new ActiveXObject('Microsoft.XMLHTTP'); }
        catch (e2) 
        {
           try {  xhr = new XMLHttpRequest();  }
           catch (e3) {  xhr = false;   }
         }
    }
    
    xhr.onreadystatechange  = function() 
    { 
       if(xhr.readyState  == 4)
       {
        if(xhr.status  == 200) 
        	afficherCandidats(this.response);
        else
        	console.log("Erreur de statut!");
        }
    }; 
    var input = document.getElementById('nom');
    var nommail = input.value;
    xhr.open("GET", "<c:out value="${pageContext.request.contextPath}"/>/rest/users/"+nommail+"/all",  true); 
	   xhr.send();
    
});

function afficherCandidats(xml) {
	var json = JSON.parse(xml);
	var props = [];
	for (let Utilisateur of json) {
		
		props.push('<button onClick="afficher('+Utilisateur.idUtilisateur+')" class="form-row btn btn-primary btn-mb btn-block" name="idutil" id="idutil" value="'+Utilisateur.idUtilisateur+'" ">Modifier : '+Utilisateur.nom+'  '+Utilisateur.prenom+' - '+Utilisateur.email+' </button><br>');
	}
	document.getElementById("results").innerHTML = props.join("");
}

function afficher(id){
	var xhr2; 
    try {  xhr2 = new ActiveXObject('Msxml2.XMLHTTP');   }
    catch (e) 
    {
        try {   xhr2 = new ActiveXObject('Microsoft.XMLHTTP'); }
        catch (e2) 
        {
           try {  xhr2 = new XMLHttpRequest();  }
           catch (e3) {  xhr2 = false;   }
         }
    }
    
    xhr2.onreadystatechange  = function() 
    { 
       if(xhr2.readyState  == 4)
       {
        if(xhr2.status  == 200) 
        	afficherCandidat(this.response);
        else
        	console.log("Erreur de statut!");
        }
    }; 

    xhr2.open("GET", "<c:out value="${pageContext.request.contextPath}"/>/rest/users/"+id,  true); 
	   xhr2.send();
};

function afficherCandidat(xml2) {
	var json2 = JSON.parse(xml2);
	var props2 = [];
		props2.push('<div class="form-row"><label for="nom" class="col col-lg-3">Nom </label>');
		props2.push('<input type="text" class="form-control col col-lg-8 offset-lg-1" name="nom" id="nom" required value="'+json2.nom+'"></div>');
		props2.push('<div class="form-row"><label for="prenom" class="col col-lg-3">Prenom </label>');
		props2.push('<input type="text" class="form-control col col-lg-8 offset-lg-1" name="prenom" id="prenom" required value="'+json2.prenom+'"></div><br>');
		props2.push('<div class="form-row"><label for="email" class="col col-lg-3">Email </label>');
		props2.push('<input type="email" class="form-control col col-lg-8 offset-lg-1" name="email" id="email" required value="'+json2.email+'"></div><br></form>');
		
		
		
	document.getElementById("afficherinfos").innerHTML = props2.join("");
}


			
	
				
			
</script>
<!-- 
	//
	<div class="form-row">
		// <label for="type" class="col col-lg-3">Stagiaire/Candidat</label> <select
			// 						class="form-control col col-lg-8 offset-lg-1" name="type"
			id="type" onchange="afficher()" required> //
			<option value="1" selected>Stagiaire</option> //
			<option value="2">Candidat libre</option> //
		</select> //
	</div>
	//
	<br> //
	<div class="form-row">
		// <label for="promo" class="col col-lg-3" id="labelselect">Promotion</label>
		<select // 						class="form-control col col-lg-8 offset-lg-1"
			name="promo" id="promo"> //
			<option selected>Choisir une promotion dans la liste</option> //
			<c:forEach var="promotion" items="${sessionScope.promos}">
// 							<option>${promotion.id }</option>
// 						</c:forEach> //
		</select> //
	</div>
	//
	<div class="form-row">
		// <input // 					type="hidden" id="actionajout" name="actionajout"
			value="stagiaire"> // <input type="submit" name="cestuntest">
		//
	</div>
	//
	</form>
 -->
	<%@include file="../../finBody.html"%>
</html>