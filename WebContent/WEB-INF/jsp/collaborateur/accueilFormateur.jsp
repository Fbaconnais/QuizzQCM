<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./entete.jsp"%>
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
				<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/collaborateur">
						Accueil Formateur <span class="sr-only">(current)</span>
				</a></li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/collaborateur/formateur/questions">Gestion
						questions</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/collaborateur/formateur/themes">Gestion
						thèmes</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/collaborateur/formateur/tests">Gestion
						tests</a></li>
			</ul>
		</div>
	</nav>

	<%@include file="./debutBody.jsp"%>
	<div class="col col-lg-7 justify-content-lg-center offset-lg-1">
		<br> <a
			href="${pageContext.request.contextPath}/collaborateur/formateur/questions"
			class="btn btn-primary btn-lg btn-block">Gestion des questions</a> <a
			href="${pageContext.request.contextPath}/collaborateur/formateur/themes"
			class="btn btn-primary btn-lg btn-block">Gestion des thèmes</a>
			<a
			href="${pageContext.request.contextPath}/collaborateur/formateur/tests"
			class="btn btn-primary btn-lg btn-block">Gestion des tests</a>
	</div>
	
		
	





	<%@include file="./finBody.html"%>
</html>