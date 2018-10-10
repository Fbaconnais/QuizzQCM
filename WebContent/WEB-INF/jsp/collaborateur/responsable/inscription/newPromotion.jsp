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
					href="${pageContext.request.contextPath}/collaborateur/responsable/accinscription">Gestion
						des inscriptions<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/responsable/resultats">Consultation
						des resultats</a></li>

			</ul>
		</div>
	</nav>
	<%@include file="../../debutBody.jsp"%>
	
	<div class="col col-lg-9">
	<br>
	<br>
	<h1>Inscription d'une nouvelle promotion</h1>
	<br>
		<c:if test="${sessionScope.messageValidation != null }">
			<h2 style="color: red;">${sessionScope.messageValidation}</h2>
			<br>
		</c:if>
	<div class="col col-lg-10 justify-content-lg-center offset-lg-1">


		<br> <br>


		<form method="post"
			action="${pageContext.request.contextPath}/collaborateur/responsable/inscription">


			<div class="form-row">
				<label for="codePromo" class="col col-lg-4"> CodePromo </label> <input
					type="text" class="form-control col col-lg-7 offset-lg-1"
					name="codePromo" id="codePromo" maxlength="8">
			</div>
			<br>
			<div class="form-row">
				<label for="libelle" class="col col-lg-4"> Description </label> <input
					type="text" class="form-control col col-lg-7 offset-lg-1"
					name="libelle" id="libelle">
			</div>
			<br> <input type="hidden" id="actionajout" name="actionajout"
				value="promotion">
			<div class="form-row">
				<input type="submit" class="btn btn-success btn-mb btn-block">
			</div>
		</form>
	</div>
	</div>
	</div>
	</div>



	<%@include file="../../finBody.html"%>
</html>