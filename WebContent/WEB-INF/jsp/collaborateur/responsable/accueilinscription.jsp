<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
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
					href="${pageContext.request.contextPath}/collaborateur/inscriptions">Gestion des inscriptions<span
						class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/collaborateur/resultats">Consultation
						des resultats</a></li>

			</ul>
		</div>
	</nav>
	<%@include file="../debutBody.jsp"%>


	<div class="col col-lg-7 justify-content-lg-center offset-lg-2">
		<br>
		<a href="${pageContext.request.contextPath}/collaborateur/inscription?action=stagiaire"><button type="button" class="btn btn-primary btn-mb btn-block">Inscrire
			un nouveau Stagiaire/Candidat</button></a>
		<a href="${pageContext.request.contextPath}/collaborateur/modif?action=stagiaire"><button type="button" class="btn btn-primary btn-mb btn-block">Modifier/Supprimer
			Stagiaire/Candidat</button></a>
		<a href="${pageContext.request.contextPath}/collaborateur/inscription?action=promotion"><button type="button" class="btn btn-primary btn-mb btn-block">Inscrire
			une nouvelle promotion</button></a>
		<a href="${pageContext.request.contextPath}/collaborateur/modif?action=promotion"><button type="button" class="btn btn-primary btn-mb btn-block">Modifier/Supprimer
			promotion</button></a>
		<a href="${pageContext.request.contextPath}/collaborateur/inscription?action=stagiairepromo"><button type="button" class="btn btn-primary btn-mb btn-block">Ajouter
			Stagiaire/Candidat à promotion</button></a>
		<a href="${pageContext.request.contextPath}/collaborateur/inscription?action=candidattest"><button type="button" class="btn btn-primary btn-mb btn-block">
			Inscrire un Stagiaire/Candidat à un test</button></a>
		<a href="${pageContext.request.contextPath}/collaborateur/inscription?action=promotest"><button type="button" class="btn btn-primary btn-mb btn-block">
			Inscrire une promotion à un test</button></a>
	</div>




	<%@include file="../finBody.html"%>
</html>