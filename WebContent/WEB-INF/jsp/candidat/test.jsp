<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>QCM - Epreuve</title>
</head>
<body>
<ul class="list-group">
  <a href="#" class="list-group-item active">
    <span class="glyphicon glyphicon-chevron-right pull-right"></span>
      Question 1
  </a>
  <a href="#" class="list-group-item">
    <span class="glyphicon glyphicon-chevron-right pull-right"></span>

      Question 2
  </a>
  <a href="#" class="list-group-item">
    <span class="glyphicon glyphicon-chevron-right pull-right"></span>
      Question 3
  </a>
  <a href="#" class="list-group-item">
    <span class="glyphicon glyphicon-chevron-right pull-right"></span>
      Question 4
  </a>
</ul>

<%-- 	<c:forEach var="question" items="${sessionScope.listeEpreuves}"> --%>
<!-- 		<div class="card" style="width: auto;"> -->
<%-- 			<img class="card-img-top" src="${epreuve.test.logoLangage }" --%>
<!-- 				alt="Card image cap"> -->
<!-- 			<div class="card-body"> -->
<%-- 				<h5 class="card-title">${epreuve.test.libelle }</h5> --%>
<%-- 				<p class="card-text">${epreuve.test.description }</p> --%>
<!-- 				<ul class="list-group list-group-flush"> -->
<!-- 					<li class="list-group-item">Date de début: -->
<%-- 						${epreuve.dateDebutValidite }</li> --%>
<!-- 					<li class="list-group-item">Date de fin: -->
<%-- 						${epreuve.dateFinValidite }</li>0 --%>
<!-- 				</ul> -->
<%-- 				<br> <a href="/QuizzQCM/test?id=${epreuve.idEpreuve}" --%>
<!-- 					class="btn btn-primary">Participer à l'épreuve</a> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<br> -->
<%-- 	</c:forEach> --%>


	<%@include file="../finBody.html"%>
</html>