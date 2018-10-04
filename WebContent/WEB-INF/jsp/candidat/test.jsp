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
<body>
<%@include file="../debutBody.html"%>
	<c:if test="${ sessionScope.listeQuestionsTirages.size()==0 }">
		<h3 style="color: red;">Une erreur est survenue, aucune question
			n'a pu être retournée.</h3>
	</c:if>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand">Questions: </a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

<!-- 		<div class="collapse navbar-collapse" id="navbarSupportedContent"> -->
<!-- 			<ul class="navbar-nav mr-auto"> -->
<ul class="nav navbar-nav list-inline">
				<c:forEach var="questionTirage"
					items="${sessionScope.listeQuestionsTirages}">
					<li class="list-inline-item">
						<div class="ecart">
							<button type="button" class="btn btn-primary"
								onClick="recuperationQuestion(${questionTirage.numordre})">${questionTirage.numordre}</a>
						</div>
					</li>
				</c:forEach>
				</ul>
<!-- 			</ul> -->
<!-- 		</div> -->
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
    	 
    	   xhr.open("GET", "<c:out value="${pageContext.request.contextPath}"/>/rest/question/"+id+"/get",  true); 
    	   xhr.send(); 
    }
    
    function traitementQuestion(xml) {
    	var json = JSON.parse(xml);
		txt = json.enonce
    	document.getElementById("test").innerHTML = txt;
		var props = [];
		for (let proposition of json.propositions) {
			console.log(proposition);
			props.push('<input type="checkbox" id="'+proposition.idProposition+'" value=""> <p>'+proposition.enonce +'</p><br>');
		}
		document.getElementById("propositions").innerHTML = props;
    }

    </script>
	<%@include file="../finBody.html"%>
</html>