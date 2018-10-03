<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
 <link rel="stylesheet" href="./CSS/ButtonTest.css">
<head>
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.css" rel="stylesheet">
<meta charset="UTF-8">
<%@include file="../entete.jsp"%>
<title>QCM - Accueil candidat</title>
</head>
<body>
	<%@include file="../debutBody.html"%>
<br>
<div class="container">
	<div class="row">
        <div class="col-md-12">
		    <a href="/QuizzQCM/epreuve"><button class="orange-circle-button" >Mes<br />epreuves<br /><span class="orange-circle-greater-than">></span></button></a>
	
        </div>   
	</div>
</div>



	<%@include file="../finBody.html"%>
</html>