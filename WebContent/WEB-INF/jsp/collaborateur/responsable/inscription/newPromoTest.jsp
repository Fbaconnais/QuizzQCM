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
					href="${pageContext.request.contextPath}/collaborateur/inscriptions">Gestion
						des inscriptions<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/resultats">Consultation
						des resultats</a></li>

			</ul>
		</div>
	</nav>
	<%@include file="../../debutBody.jsp"%>
	<div class="col col-lg-9">
		<br> <br>
		<h1>Inscription promotion à un test</h1>
		<br>
		<div class="col col-lg-10 justify-content-lg-center offset-lg-1">


			<br> <br>
			<form method="post"
				action="${pageContext.request.contextPath}/collaborateur/inscription">

				<div class="form-row">
					<label for="promo" class="col col-lg-3">Promotion</label> <select
						class="form-control col col-lg-9" name="promo" id="promo" required>
						<option selected>Choisir une promotion dans la liste</option>
						<c:forEach var="promotion" items="${sessionScope.promos}">
							<option>${promotion.id }</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<div class="form-row">
					<label for="test" class="col col-lg-3">Test</label> <select
						class="form-control col col-lg-9" name="test" id="test" required>
						<option selected>Choisir un test dans la liste</option>
						<c:forEach var="test" items="${sessionScope.tests}">
							<option name="${test.idTest} id="
								${test.idTest}" value="${test.idTest}">${test.libelle }</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<div class="form-row">
					<label for="dateDebutValidite" class="col col-lg-3">Date
						debut validite</label> <input type="date"
						class="form-control col col-lg-3" name="dateDebutValidite"
						required><label for="HeureDebutValidite"
						class="offset-lg-2"> Heure :</label> <input type="time"
						class="form-control col col-lg-2 offset-lg-1"
						name="HeureDebutValidite" required>
				</div>
				<br>
				<div class="form-row">
					<label for="dateFinValidite" class="col col-lg-3">Date fin
						validite</label> <input type="date" class="form-control col col-lg-3"
						name="dateFinValidite" required> <label
						for="HeureFinValidite" class="offset-lg-2"> Heure :</label> <input
						type="time" class="form-control col col-lg-2 offset-lg-1"
						name="HeureFinValidite" required>
				</div>
				<input type="hidden" id="actionajout" name="actionajout"
					value="promotest">
				<div class="form-row">
					<input type="submit">
				</div>
			</form>
		</div>
	</div>
	</div>
	</div>


	<%@include file="../../finBody.html"%>
</html>