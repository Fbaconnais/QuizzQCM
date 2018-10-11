<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>Consultation résultats</title>
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
				<li class="nav-item "><a class="nav-link" href="${pageContext.request.contextPath}/candidat">
						Espace Candidat 
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/candidat/epreuve">Choix des épreuves</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/candidat/consultation">Consultation des résultats<span class="sr-only">(current)</span></a></li>
			</ul>
		</div>
	</nav>
	<%@include file="./debutbody.jsp"%>
	<c:set var="baseURL" value="${pageContext.request.contextPath}" />
	<c:set var="idCandidat" value="${sessionScope.user.idUtilisateur}" />
	<div class="col col-lg-9 justify-content-lg-center ">
		<br>
		<h1>Consultation des resultats</h1>
		<br>
		<div id="afficherinfos">
			<button onclick="afficherTestsUtil(${idCandidat})"
				class="btn btn-primary btn-lg btn-block">Consulter mes
				resultats</button>
		</div>
		<div id="succes" style="color: green"></div>
		<div id="echec" style="color: red"></div>

		<br>
	</div>
	</div>
	<br>
	<div class="col col-lg-10 offset-lg-1" id="tests"></div>

	<script>
		var path = "${baseURL}";
	
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/affichageTests.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/createXHR.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/succesreponse.js"></script>

	<%@include file="../finBody.html"%>
</html>