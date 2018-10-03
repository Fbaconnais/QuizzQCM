<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../../entete.jsp"%>
<title>gestions des inscriptions</title>
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
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur">
						Accueil Responsable </a></li>

				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/inscriptions">Gestion
						des inscriptions<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/resultats">Consultation
						des resultats</a></li>

			</ul>
		</div>
	</nav>
	<%@include file="../../debutBody.jsp"%>
	<div class="col col-lg-9">
		<br> <br>
		<h1>Inscription promotion à un test</h1>
		<br>
		<div class="col col-lg-10 justify-content-lg-center offset-lg-1">

			<form method="post"
				action="${pageContext.request.contextPath}/collaborateur/inscription">

				<div class="form-row">
					<label for="promo" class="col col-lg-3">Promotion</label> <select
						class="form-control col col-lg-9" name="promo" id="promo">
						<option selected>Choisir une promotion dans la liste</option>
						<c:forEach var="promotion" items="${sessionScope.promos}">
							<option>${promotion.id }</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<div class="form-row">
					<label for="test" class="col col-lg-3">Test</label> <select
						class="form-control col col-lg-9" name="test" id="test">
						<option selected>Choisir un test dans la liste</option>
						<c:forEach var="test" items="${sessionScope.tests}">
							<option name="${test.idTest} id="
								${test.idTest}" value="${test.idTest}">${test.libelle }</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<div class="form-row">
					<label for="dateDebutValidite" class="col col-lg-3">Date
						debut validite</label> <input type="date"
						class="form-control col col-lg-9" name="dateDebutValidite"
						required>
				</div>
				<br>
				<div class="form-row">
					<label for="dateFinValidite" class="col col-lg-3">Date fin
						validite</label> <input type="date" class="form-control col col-lg-9"
						name="dateFinValidite" required>
				</div>
				<input type="hidden" id="myhidden" name="myhidden" value="vide"> <input
					type="hidden" id="action" name="action" value="promotest">
				<input type="submit">
			</form>
		</div>
	</div>
	</div>
	
	
	<script>
	
	$('#test').change(function () {
        var str = "";
        $('select option:selected').each(function () {
              str += $(this).id();
            });
         $('#myhidden').value.replace(str);
         
      })
      .change();
	
	
	
	</script>

	<%@include file="../../finBody.html"%>
</html>