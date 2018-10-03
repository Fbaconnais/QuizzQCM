<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./entete.html"%>
<title>QCM - Accueil formateur</title>

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
				<li class="nav-item active"><a class="nav-link" href="#">
						Accueil Formateur <span class="sr-only">(current)</span>
				</a></li>

				<li class="nav-item"><a class="nav-link" href="questions">Gestion
						questions</a></li>
				<li class="nav-item"><a class="nav-link" href="themes">Gestion
						thèmes</a></li>
				<li class="nav-item"><a class="nav-link" href="tests">Gestion
						tests</a></li>
			</ul>
		</div>
	</nav>

	<%@include file="../debutBody.html"%>
	
	<div class="row">
		<div class="col col-lg-4">
		<div class="row"><h1>${sessionScope.user.nom}</h1></div>
		<div class="row"><h1>${sessionScope.user.prenom}</h1></div>
		<div class="row"><a href="modif"><button>Modifier vos informations</button></a></div>
		<div class="row"><a href="deconnection"><button>Déconnection</button></a></div>
		
		</div>
	
	</div>
	




	<%@include file="../finBody.html"%>
</html>