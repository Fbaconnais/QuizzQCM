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
				<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/candidat/epreuve">Choix des épreuves<span class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/candidat/consultation">Consultation des résultats</a></li>
			</ul>
		</div>
	</nav>
	<%@include file="./debutbody.jsp"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}/rest/" />
	<div class="container text-center">
		<h1 class="display-4">Epreuves</h1>
		<hr>
	
	<c:choose>
		<c:when test="${empty sessionScope.listeEpreuves }">
			<div class="row">Aucune épreuve n'est programmée pour vous</div>
		</c:when>
		<c:otherwise>
					<%int compteurLigne = 0; %>
			<c:forEach var="epreuve" items="${sessionScope.listeEpreuves}">
	<%if (compteurLigne%4 == 0) {
	out.write("<div class=\"row\">");
	}%>
			<div class="col-lg-3 col-md-4 col-xs-6">
				<div class="card" style="width:275px;">
					<img class="card-img-top"
						src="${pageContext.request.contextPath}${epreuve.test.logoLangage }"
						alt="Logo du langage de programmation">
					<div class="card-body">
						<h5 class="card-title">${epreuve.test.libelle }</h5>
						<p class="card-text">${epreuve.test.description }</p>
						<ul class="list-group list-group-flush">
							<li class="list-group-item">Date de début:
								${epreuve.dateDebutValidite }</li>
							<li class="list-group-item">Date de fin:
								${epreuve.dateFinValidite }</li>
						</ul>
						<c:choose>
							<c:when test="${epreuve.etat == 'EA'}">
								<br> <button type="button" onclick="confirmerChoixEpreuve(${epreuve.idEpreuve})"
								class="btn btn-primary">Participer à l'épreuve</button>
							</c:when>
							<c:otherwise>
								<br> <button type="button" onclick="confirmerChoixEpreuve(${epreuve.idEpreuve})"
								class="btn btn-primary">Reprendre l'épreuve</button>
							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
				</div>
				<%compteurLigne++; %>
				<%if (compteurLigne%4 == 0) {
					out.write("</div>");
					}%>
					
			</c:forEach>

			<br>

		</c:otherwise>
	</c:choose>
	</div>
	<script>
	var path= "${baseURL}";
	</script>
	<%@include file="../finBody.html"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ChoixEpreuve.js"></script>
</body>
</html>