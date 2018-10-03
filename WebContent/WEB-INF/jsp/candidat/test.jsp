<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
 <link rel="stylesheet" href="./CSS/ButtonTest.css">
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>QCM - Epreuve</title>
</head>
<body>
	<%
		int compteurQuestion = 0;
	%>
	<c:if test="${ sessionScope.listeQuestionsTirages.size()==0 }">
		<h3 style="color: red;">Une erreur est survenue, aucune question
			n'a pu être retournée.</h3>
	</c:if>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Questions: </a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<c:forEach var="questionTirage"
					items="${sessionScope.listeQuestionsTirages}">
					<li class="nav-item active">
					<div class="ecart">
						<button type="button" class="btn btn-primary"
							action="afficherQuestion(${questionTirage.numordre})">${questionTirage.numordre}</a>
					</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</nav>


	<c:forEach var="questionTirage"
		items="${sessionScope.listeQuestionsTirages}">
		<div class="card" style="display: none;"
			id="${questionTirage.numordre}">
			<div class="card-body">${questionTirage.question.enonce }</div>
			<div class="card-footer">
				<ul>
					<c:forEach var="proposition"
						items="${questionTirage.question.propositions}">
						<li>${proposition.enonce}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</c:forEach>

	<script>
		function afficherQuestion(id) {
			var x = document.getElementById(id);
			if (x.style.display === "none") {
				x.style.display = "flex";
			} else {
				x.style.display = "none";
			}
		}
	</script>
	<%@include file="../finBody.html"%>
</html>