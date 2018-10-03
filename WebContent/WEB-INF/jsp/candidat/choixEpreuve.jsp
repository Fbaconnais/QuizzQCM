<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>QCM - Choix de l'épreuve</title>
</head>
<body>
	<%@include file="../debutBody.html"%>

	<div class="container text-center">
		<h1 class="display-4">Epreuves</h1>
		<hr>
	</div>

	<c:choose>
		<c:when test="${empty sessionScope.listeEpreuves }">
			<div class="row">Aucune épreuve n'est programmée pour vous</div>
		</c:when>
		<c:otherwise>
			<c:forEach var="epreuve" items="${sessionScope.listeEpreuves}">
				<div class="card" style="width: 255px">
					<img class="card-img-top"
						src="${pageContext.request.contextPath}${epreuve.test.logoLangage }"
						alt="Logo du langage de programmation"
						style="width: 250px; height: 250px;">
					<div class="card-body">
						<h5 class="card-title">${epreuve.test.libelle }</h5>
						<p class="card-text">${epreuve.test.description }</p>
						<ul class="list-group list-group-flush">
							<li class="list-group-item">Date de début:
								${epreuve.dateDebutValidite }</li>
							<li class="list-group-item">Date de fin:
								${epreuve.dateFinValidite }</li>
						</ul>
						<br> <a href="/QuizzQCM/test?id=${epreuve.idEpreuve}"
							class="btn btn-primary">Participer à l'épreuve</a>
					</div>
				</div>
			</c:forEach>

			<br>

		</c:otherwise>
	</c:choose>

	<%@include file="../finBody.html"%>
</body>
</html>