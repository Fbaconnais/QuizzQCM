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
					href="${pageContext.request.contextPath}/collaborateur/responsable/accinscription">Gestion des inscriptions<span
						class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/collaborateur/responsable/resultats">Consultation
						des resultats</a></li>

			</ul>
		</div>
	</nav>
	<%@include file="../../debutBody.jsp"%>
<div class="col col-lg-9">
		<br> <br>
		<h1>Inscription d'un nouveau stagiaire/candidat</h1>
		<br>
			<c:if test="${sessionScope.messageValidation != null }">
			<h2 style="color: red;">${sessionScope.messageValidation}</h2>
			<br>
		</c:if>
		<div class="col col-lg-10 justify-content-lg-center offset-lg-1">
		
			
			<br>
			<br>
			<form method="post"
				action="${pageContext.request.contextPath}/collaborateur/responsable/inscription">

				<div class="form-row">
					<label for="nom" class="col col-lg-3">Nom </label> <input type="text"
						class="form-control col col-lg-8 offset-lg-1" name="nom" id="nom" required>			
				</div>
				<br>
				<div class="form-row">
					<label for="prenom" class="col col-lg-3">Prenom </label> <input type="text"
						class="form-control col col-lg-8 offset-lg-1" name="prenom" id="prenom" required>			
				</div>
				<br>
				<div class="form-row">
					<label for="email" class="col col-lg-3">Email </label> <input type="email"
						class="form-control col col-lg-8 offset-lg-1" name="email" id="email" required>			
				</div>
				<br>
				<div class="form-row">
					<label for="type" class="col col-lg-3">Stagiaire/Candidat</label> <select
						class="form-control col col-lg-8 offset-lg-1" name="type" id="type" onchange="afficher()" required>
						<option value="1" selected>Stagiaire</option>
						<option value="2" >Candidat libre</option>
					</select>
				</div>
				<br>
				<div class="form-row" >
					<label for="promo" class="col col-lg-3" id="labelselect">Promotion</label> <select
						class="form-control col col-lg-8 offset-lg-1" name="promo" id="promo" >
						<option selected>Choisir une promotion dans la liste</option>
						<c:forEach var="promotion" items="${sessionScope.promos}">
							<option>${promotion.id }</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<div class="form-row">
				<input
					type="hidden" id="actionajout" name="actionajout" value="stagiaire">
				<input type="submit" name="cestuntest" class="btn btn-success btn-mb btn-block">
				</div>
			</form>
		</div>
	</div>
	</div>



	<script>
	function afficher(){
		var type = document.getElementById('type');
		var promos = document.getElementById('promo');
		var label = document.getElementById('labelselect')
		if (type.value == 1){
			promos.style.display = 'inline';
			label.style.display = 'inline';
		} else {
			promos.style.display = 'none';
			label.style.display = 'none';
		}
	}
	
	</script>

	<%@include file="../../finBody.html"%>
</html>