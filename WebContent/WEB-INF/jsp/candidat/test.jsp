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
<%int compteurQuestion = 0; %>
	<c:if test="${ sessionScope.listeQuestionsTirages.size()==0 }">
		<h3 style="color: red;">Une erreur est survenue, aucune question
			n'a pu être retournée.</h3>
	</c:if>
<nav class="navbar navbar-default">
<div class="container-fluid">
<div class="navbar-header">
<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" date-target="#bs-example-navbar-collapse-1" aria-expanded="false">
<c:forEach var="questionTirage"	items="${sessionScope.listeQuestionsTirages}">
<a href="" class="btn btn-info"><%out.write(compteurQuestion);%></a>
</c:forEach>
</button>
</div>
</div>
</nav>
<%-- 		<c:forEach var="questionTirage" --%>
<%-- 			items="${sessionScope.listeQuestionsTirages}"> --%>
<!-- 			<div class="card"> -->
<%-- 				<div class="card-body">${questionTirage.question.enonce }</div> --%>
<!-- 				<div class="card-footer"> -->
<!-- 					<ul> -->
<%-- 						<c:forEach var="proposition" --%>
<%-- 							items="${questionTirage.question.propositions}"> --%>
<%-- 							<li>${proposition.enonce}</li> --%>
<%-- 						</c:forEach> --%>
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 			</div> -->
<%-- 		</c:forEach> --%>

	<%@include file="../finBody.html"%>
</html>