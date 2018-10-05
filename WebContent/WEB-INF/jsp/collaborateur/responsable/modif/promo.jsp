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
<body onload="listepromos()">
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
		<h1>Modification d'une promotion</h1>
		<br>

		<c:if test="${sessionScope.messageValidation != null }">
			<h2 style="color: red;">${sessionScope.messageValidation}</h2>
			<br>
		</c:if>


		<form method="post"
			action="${pageContext.request.contextPath}/collaborateur/responsable/modif">


			<input type="hidden" id="actionmodif" name="actionmodif"
				value="promo"> <br>
			<div id="results"></div>
		</form>

	</div>
	</div>
</div>


	<script type="text/javascript">
function listepromos() {
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
        	afficherPromos(this.response);
        else
        	console.log("Erreur de statut!");
        }
    }; 

    xhr.open("GET", "<c:out value="${pageContext.request.contextPath}"/>/rest/promos/all",  true); 
	   xhr.send();
    
};

function afficherPromos(xml) {
	var json = JSON.parse(xml);
	var props = [];
	
	for (let Promotion of json) {
		
		props.push('<button type="submit" class="btn btn-primary btn-mb col-xs-12 col-md-6 col-lg-3 " style="border-width: 5px;border-color:white;" name="idpromo" id="idpromo" value="'+Promotion.id+'" "> Modifier : '+Promotion.id+' </button>');
		
	}
	document.getElementById("results").innerHTML = props.join("");
}
</script>


	<%@include file="../../finBody.html"%>
</html>