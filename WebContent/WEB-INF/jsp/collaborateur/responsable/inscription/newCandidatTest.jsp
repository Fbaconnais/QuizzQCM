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
					href="${pageContext.request.contextPath}/collaborateur/responsable/accinscription">Gestion
						des inscriptions<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/collaborateur/responsable/resultats">Consultation
						des resultats</a></li>

			</ul>
		</div>
	</nav>
	<%@include file="../../debutBody.jsp"%>
	<div class="col col-lg-9">
		<br> <br>
		<h1>Inscription candidat à un test</h1>
		<br>
		<c:if test="${sessionScope.messageValidation != null }">
			<h2 style="color: red;">${sessionScope.messageValidation}</h2>
			<br>
		</c:if>
		<div id="succes" style="color: green"></div>
		<div id="echec" style="color: red"></div>
		<br>
		<div class="col col-lg-10 justify-content-lg-center offset-lg-1">
			<div class="form-row">
				<label for="nom" class="col col-lg-4">Recherche par nom/mail
				</label> <input type="text" class="form-control col col-lg-7 offset-lg-1"
					name="nom" id="nom">
			</div>
			<br>
			<form method="post"
				action="${pageContext.request.contextPath}/collaborateur/responsable/inscription">

				<div class="form-row">
					<label for="test" class="col col-lg-3">Test</label> <select
						class="form-control col col-lg-9" name="test" id="test" required>
						<option selected>Choisir un test dans la liste</option>
						<c:forEach var="test" items="${sessionScope.tests}">
							<option id="${test.idTest}" value="${test.idTest}">${test.libelle }</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<div class="form-row">
					<label for="dateDebutValidite" class="col col-lg-3">Date
						debut validite (attention au format)</label> <input type="text"
						class="form-control col col-lg-4" name="dateDebutValidite"
						placeholder="/!\ JJ-MM-AAAA /!\" required><label
						for="HeureDebutValidite" class="offset-lg-1"> Heure :</label> <input
						type="text" class="form-control col col-lg-2 offset-lg-1"
						name="HeureDebutValidite" placeholder="/!\ HH:MM /!\" required>
				</div>
				<br>
				<div class="form-row">
					<label for="dateFinValidite" class="col col-lg-3">Date fin
						validite attention au format)</label> <input type="text"
						class="form-control col col-lg-4" name="dateFinValidite"
						id="date2" required placeholder="/!\ JJ-MM-AAAA /!\"> <label
						for="HeureFinValidite" class="offset-lg-1"> Heure :</label> <input
						type="text" class="form-control col col-lg-2 offset-lg-1"
						name="HeureFinValidite" placeholder="/!\ HH:MM /!\" required>
				</div>
			
				<input type="hidden" id="actionajout" name="actionajout"
					value="candidattest"> <br>
				<div id="results"></div>
			</form>
		</div>

	</div>
	</div>
	</div>

	<script type="text/javascript">
	
		function createXHR() {
			if (window.XMLHttpRequest) {
				xhr = new XMLHttpRequest();
			} else if (window.ActiveXObject) //  Internet Explorer
			{
				xhr = new ActiveXObject("Msxml2.XMLHTTP");
			}

			return xhr;

		}

		jQuery("input[name='nom']").on(
				"input",
				function() {
					var xhr = createXHR();

					xhr.onreadystatechange = function() {
						if (xhr.readyState == 4) {
							if (xhr.status == 200)
								afficherCandidats(this.response);
							else
								console.log("Erreur de statut!");
						}
					};
					var input = document.getElementById('nom');
					var nommail = input.value;
					xhr.open("GET",
							"<c:out value="${pageContext.request.contextPath}"/>/rest/users/"
									+ nommail + "/all", true);
					xhr.send();

				});

		function afficherCandidats(xml) {
			var json = JSON.parse(xml);
			var props = [];
			var x;
			for (x = 0; x < json.length; x++) {

				props
						.push('<button type="submit" class="form-row btn btn-primary btn-mb btn-block" name="idutil" id="idutil" value="'+json[x].idUtilisateur+'" ">Incrire : '
								+ json[x].nom
								+ '  '
								+ json[x].prenom
								+ ' - '
								+ json[x].email + ' </button><br>');
			}
			document.getElementById("results").innerHTML = props.join("");
		}
		function succes(reponse) {
			document.getElementById("succes").innerHTML = reponse;
			document.getElementById("echec").innerHTML = "";
		}
		function echec(codeReponse, reponse) {
			document.getElementById("echec").innerHTML = reponse;
			document.getElementById("succes").innerHTML = "";
		}
	</script>


	<%@include file="../../finBody.html"%>
</html>