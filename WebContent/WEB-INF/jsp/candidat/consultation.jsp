<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>Consultation résultats</title>
</head>
<body>
	<%@include file="./debutbody.jsp"%>
	<c:set var="baseURL" value="${pageContext.request.contextPath}" />
	<c:set var="idCandidat" value="${sessionScope.user.idUtilisateur}" />
	<div class="col col-lg-9 justify-content-lg-center ">
		<br>
		<h1>Consultation des resultats</h1>
		<br>
		<div id="succes" style="color: green"></div>
		<div id="echec" style="color: red"></div>
		<br>
		<div class="row">
			<div class="col col-lg-10 offset-lg-1" id="tests">
				<div id="results"></div>
			</div>
		</div>
	</div>
	<script>
		var path = "${baseURL}";
		afficherTestsUtil(${idCandidat});
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/affichageTests.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/createXHR.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/succesreponse.js"></script>

	<%@include file="../finBody.html"%>
</html>