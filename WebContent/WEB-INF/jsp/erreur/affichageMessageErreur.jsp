<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>QCM - Erreur</title>
</head>
<body>
	<%@include file="../debutBody.html"%>
	<div class="row justify-content-lg-center">
		<h1>Une erreur est survenue, merci de nous contacter en nous
			donnant le code suivant :</h1>
	</div>
	<div class="row justify-content-lg-center">${sessionScope.erreur}</div>
	
	<form method="post" action="erreur">
	<input type="submit" name="revenir a l'accueil" value="revenir à l'accueil">
	
	</form>
	<%@include file="../finBody.html"%>
</html>