<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>Gestion Tests</title>
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
				<li class="nav-item "><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur"> Accueil Formateur 
				</a></li>

				<li class="nav-item "><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/formateur/questions">Gestion
						questions</a></li>
				<li class="nav-item "><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/formateur/themes">Gestion
						thèmes</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/formateur/tests">Gestion
						tests<span class="sr-only">(current)</span></a></li>
			</ul>
		</div>
	</nav>

	<%@include file="../debutBody.jsp"%>
<div class="col col-lg-7 justify-content-lg-center offset-lg-1">
		<br>
		<h3>Gestion des tests</h3>
		<br>
		<button onclick="newTest()"
			class="btn btn-primary btn-lg btn-block">Creer un nouveau
			test</button>
		<button onclick="listeTests()"
			class="btn btn-primary btn-lg btn-block">Editer/supprimer
			un test</button>
		<br>
		<div id="succes" style="color: green"></div>
		<div id="echec" style="color: red"></div>

	</div>
	</div>
	<br>
	<div class=row>
		<div id="results" class="col col-lg-10 offset-lg-1"></div>
	</div>








	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/createXHR.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/gestionTests.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/succesreponse.js"></script>
	<%@include file="../finBody.html"%>
</html>