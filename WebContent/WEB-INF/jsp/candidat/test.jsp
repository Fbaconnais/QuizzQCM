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
								onClick="afficherQuestion(${questionTirage.numordre})">${questionTirage.numordre}</a>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</nav>
<script>

    function afficherQuestion(id) {

        var httpRequest = false;

        httpRequest = new XMLHttpRequest();

        if (!httpRequest) {
            alert('Abandon. Impossible de créer une instance XMLHTTP');
            return false;
        }
        httpRequest.onreadystatechange = function() { alertContents(httpRequest); };
        httpRequest.open('GET', "http://localhost:8080/QuizzQCM/question/"+id+"/get", true);
        httpRequest.send();

    }

    function alertContents(httpRequest) {

        if (httpRequest.readyState == XMLHttpRequest.DONE) {
            if (httpRequest.status == 200) {
                alert(httpRequest.responseText);
            } else {
                alert('Un problème est survenu avec la requête.');
            }
        }

    }
    </script>
	<%@include file="../finBody.html"%>
</html>