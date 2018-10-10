<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/ButtonTest.css">
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>QCM - Epreuve</title>
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
				<li class="nav-item active"><a class="nav-link" href="collaborateur">
						Espace Candidat <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="collaborateur/formateur/questions">Choix des épreuves</a></li>
				<li class="nav-item"><a class="nav-link" href="collaborateur/formateur/themes">Consultation des résultats</a></li>
			</ul>
		</div>
	</nav>
	<%@include file="./debutbody.jsp"%>
	
	<c:set var="baseURL" value="${pageContext.request.contextPath}/rest/" />
	<iframe src="${pageContext.request.contextPath}/audio/silence.mp3"
		allow="autoplay" id="audio" style="display: none"></iframe>
	<audio autoplay="autoplay" preload="auto" loop
		src="${pageContext.request.contextPath}/audio/Koh-Lanta.mp3"></audio>
	<input type="hidden" value="${requestScope.idEpreuve}" id="epreuve">
	<c:if test="${ sessionScope.listeQuestionsTirages.size()== 0}">
		<h3 style="color: red;">Une erreur est survenue, aucune question
			n'a pu être retournée.</h3>
	</c:if>
	<div id="succes"></div>
	<div id="echec"></div>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand">Questions: </a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<ul class="nav navbar-nav list-inline">
			<c:forEach var="questionTirage"
				items="${sessionScope.listeQuestionsTirages}">
				<li class="list-inline-item"><c:choose>
						<c:when test="${questionTirage.estMarquee == 'true'}">
							<div class="ecart">
								<button type="button" class="btn btn-warning"
									onClick="recuperationQuestion(${questionTirage.question.idQuestion},${requestScope.idEpreuve})"
									id="button${questionTirage.question.idQuestion}"
									value="pasMarquee">${questionTirage.numordre}</button>
							</div>
						</c:when>
						<c:when test="${questionTirage.estMarquee == 'false'}">
							<div class="ecart">
								<button type="button" class="btn btn-primary"
									onClick="recuperationQuestion(${questionTirage.question.idQuestion},${requestScope.idEpreuve})"
									id="button${questionTirage.question.idQuestion}"
									value="marquee">${questionTirage.numordre}</button>
							</div>
						</c:when>
					</c:choose></li>
			</c:forEach>
			<li>
				<div class="ecart">
					<button type="button" class="btn btn-danger" onClick="recapTest()">
						Fin</button>
				</div>
			</li>

			<li id="compteRebour_affiche" style="float: right;">
				<div class="ecart"></div>
			</li>

		</ul>

	</nav>
	<div id="marquage"></div>
	<p id="test" class="cadre"></p>
	<div id="propositions"></div>
	<div id="timer"></div>
	<script>
	var path= "${baseURL}"; 
	var idEpreuve = "${requestScope.idEpreuve}";
	var tpsEcoule = "${requestScope.tpsEcoule}";
	console.log(tpsEcoule);
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/Test.js"></script>
	<%@include file="../finBody.html"%>
</html>
