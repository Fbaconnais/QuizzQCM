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
		<h1>Fous n'afez pas l'autorisation d'accéder à cette page</h1>
	</div>
	<form method="post">
		<input type="submit" value="revenir au login">
	</form>

	<%@include file="../finBody.html"%>
</html>