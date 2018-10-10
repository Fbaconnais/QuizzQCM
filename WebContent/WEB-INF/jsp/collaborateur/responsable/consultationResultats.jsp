<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>Consultation de résultats</title>
</head>
<body>
	<c:set var="baseURL" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExample08" aria-controls="navbarsExample08"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse justify-content-md-center"
			id="navbarsExample08">
			<ul class="navbar-nav">
				<li class="nav-item "><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur">
						Accueil Responsable </a></li>

				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/responsable/accinscription">Gestion
						des inscriptions</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/responsable/resultats">Consultation
						des resultats<span class="sr-only">(current)</span>
				</a></li>

			</ul>
		</div>
	</nav>
	<%@include file="../debutBody.jsp"%>

	<div class="col col-lg-9 justify-content-lg-center ">
		<br>
		<h1>Consultation des resultats</h1>
		<br>
		<div id="succes" style="color: green"></div>
		<div id="echec" style="color: red"></div>
		<br>

		<div class="col col-lg-10 justify-content-lg-center offset-lg-1">
			<div id="afficherinfos">
				<div class="form-row">
					<label for="nom" class="col col-lg-4">Recherche par
						nom/mail </label> <input type="text"
						class="form-control col col-lg-7 offset-lg-1" name="nom" id="nom">
				</div>
				<br>
				<div class="form-row">
					<label for="promo" class="col col-lg-4">Recherche par promo
					</label> <input type="text" class="form-control col col-lg-7 offset-lg-1"
						name="promo" id="promo">
				</div>
				<br>
			</div>
		</div>
	</div>
	</div>
	<br>
	<div class="row">
		<div class="col col-lg-10 offset-lg-1" id="tests">
			<div id="results"></div>
		</div>
	</div>


	<script>
		var path = "${baseURL}";
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/createXHR.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/afficherListeCandidats.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/afficherListePromos.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/succesreponse.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/affichageTests.js"></script>



	<%@include file="../finBody.html"%>
</html>