<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="./CSS/ButtonTest.css">
<head>
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.css"
	rel="stylesheet">
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>QCM - Accueil candidat</title>
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
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/candidat"> Espace
						Candidat <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/candidat/epreuve">Choix
						des épreuves</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/candidat/consultation">Consultation
						des résultats</a></li>
			</ul>
		</div>
	</nav>
	<%@include file="./debutbody.jsp"%>
	<br>
	<div class="col col-lg-9">
		<br> <br>
		<h1>Accueil candidat</h1>
		<br> <a class="btn btn-primary btn-mb btn-block"
			href="${pageContext.request.contextPath}/candidat/epreuve"> Mes
			epreuves</a>
			<br> <a class="btn btn-primary btn-mb btn-block"
			href="${pageContext.request.contextPath}/candidat/consultation"> Mes
			resultats</a>
	</div>



	<%@include file="../finBody.html"%>
</html>