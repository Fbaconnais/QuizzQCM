<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="./CSS/ButtonTest.css">
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>QCM - Epreuve</title>
</head>
<body onload="rebour(3600)">
	<%@include file="../debutBody.html"%>
	<iframe src="${pageContext.request.contextPath}/audio/silence.mp3"
		allow="autoplay" id="audio" style="display: none"></iframe>
	<audio autoplay="autoplay" preload="auto" loop
		src="${pageContext.request.contextPath}/audio/Koh-Lanta.mp3"></audio>
	<input type="hidden" value="${requestScope.idEpreuve}" id="epreuve">
	<c:if test="${ sessionScope.listeQuestionsTirages.size()== 0}">
		<h3 style="color: red;">Une erreur est survenue, aucune question
			n'a pu être retournée.</h3>
	</c:if>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-bran	d">Questions: </a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<ul class="nav navbar-nav list-inline">
			<c:forEach var="questionTirage"
				items="${sessionScope.listeQuestionsTirages}">
				<li class="list-inline-item">
					<div class="ecart">
						<button type="button" class="btn btn-primary"
							onClick="recuperationQuestion(${questionTirage.question.idQuestion})">${questionTirage.numordre}</a>
					</div>
				</li>
			</c:forEach>
			<li>
			<div class="ecart">
				<button type="button" class="btn btn-primary" onClick="recapTest()">
					Fin</a>
					</div>
			</li>

			<li id="compteRebour_affiche" style="float: right;">
						<div class="ecart">
						
						</div>
			</li>
				
		</ul>

	</nav>

	<p id="test" class="cadre"></p>
	<div id="propositions"></div>
	<script>

    function recuperationQuestion(id) {
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
    	        	traitementQuestion(this.response);
    	        else
    	        	console.log("Erreur de statut!");
    	        }
    	    }; 
    	 
    	   xhr.open("GET", '<c:out value="${pageContext.request.contextPath}"/>/rest/question/'+id+'/get',  true); 
    	   xhr.send(); 
    }
    
    function traitementQuestion(xml) {
    	var json = JSON.parse(xml);
		var props = [];
		var txt;
		var idEpreuve;
		console.log(json);
		txt = json.enonce
    	document.getElementById("test").innerHTML = txt;
		idEpreuve = document.getElementById("epreuve");
		console.log(idEpreuve.value);
		for (let proposition of json.propositions) {
			console.log(proposition);
			txt = '<input type="checkbox" id="'+proposition.idProposition+'" onClick="gestionPropositionCandidat('+proposition.idProposition+','+json.idQuestion+','+idEpreuve.value+')"> <p>'+proposition.enonce +'</p><br>';
			props.push(txt);
		}
		document.getElementById("propositions").innerHTML = props.join("");
    }
    
    function recapTest() {
    	var txt;
    	var nbQrep;
    	var boutonFin;
    	txt = 'Vous avez répondu à '+nbQrep+' questions. Êtes-vous sûr de vouloir valider votre test?';
    	boutonFin = '<button type=button onClick="">Terminer le test</button>';
    	document.getElementById("test").innerHTML = txt;
    	document.getElementById("propositions").innerHTML = boutonFin;
    	
    }
    
    function gestionPropositionCandidat(idProposition, idQuestion, idEpreuve){
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
				//TODO
	        else
	        	console.log("Erreur de statut!");
	        }
	    }; 
	 
	   xhr.open("GET", '<c:out value="${pageContext.request.contextPath}"/>/rest/reponse/'+idProposition+'/'+idQuestion+'/'+idEpreuve+'/gestion',  true); 
	   xhr.send(); 
    }
    
    function rebour(tps){
    	if (tps>0) {
    		var heure = Math.floor(tps/3600);
    		if(heure >= 24){
    		var jour = Math.floor(heure/24);
    		var moins = 86400*jour;
    		var heure = heure-(24*jour);
    		}else{
    		var jour = 0;
    		var moins = 0;
    		}
    	moins = moins+3600*heure;
    	var minutes = Math.floor((tps-moins)/60);
    	moins = moins + 60*minutes;
    	var secondes = tps-moins;
    	minutes = ((minutes < 10) ? "0" : "") + minutes;
    	secondes = ((secondes < 10) ? "0" : "") + secondes;
    	document.getElementById("compteRebour_affiche").innerHTML = '<button type="button" class="btn btn-primary">'+heure+':'+minutes+':'+secondes+'</button>';
    	var restant = tps-1;
    	setTimeout("rebour("+restant+")", 1000);
    			}else{
    	alert("Fin du test, merci de votre participation.");
    	}
    	}
    </script>
	<%@include file="../finBody.html"%>
</html>
x
