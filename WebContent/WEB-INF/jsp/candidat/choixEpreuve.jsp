<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="./CSS/ButtonTest.css">
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>QCM - Choix de l'épreuve</title>
</head>
<body>
	<%@include file="../debutBody.html"%>

		<div class="container text-center">
			<div class="row justify-content-md-center">
				<div class="col col-lg-12 text-center">
	<div class="row justify-content-lg-center">
		<h1 class="display-4">Epreuves</h1>
	</div>
	<hr>
	<c:choose>
		<c:when test="${empty sessionScope.listeEpreuves }">
			<div class="row">Aucune épreuve n'est programmée pour vous</div>
		</c:when>
		<c:otherwise>
			<div class="card-group">
				<c:forEach var="epreuve" items="${sessionScope.listeEpreuves}">
					<div class="card" style="width: auto;">
						<img class="card-img-top"
							src="${epreuve.test.logoLangage }"
							alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title">${epreuve.test.libelle }</h5>
							<p class="card-text">${epreuve.test.description }</p>
							<ul class="list-group list-group-flush">
								<li class="list-group-item">Date de début:
									${epreuve.dateDebutValidite }</li>
								<li class="list-group-item">Date de fin:
									${epreuve.dateFinValidite }</li>
							</ul>
							<br>
							<a href="/QuizzQCM/test?id=${epreuve.idEpreuve}" class="btn btn-primary">Participer à l'épreuve</a>
						</div>
					</div>
					<br>
				</c:forEach>
			</div>
		</c:otherwise>
	</c:choose>
				</div>
			</div>
		</div>
	<%@include file="../finBody.html"%>
</body>
</html>