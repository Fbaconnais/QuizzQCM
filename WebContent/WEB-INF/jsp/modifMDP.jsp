<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./entete.jsp"%>
<title>Modifier mes informations</title>
</head>
<body>
	<%@include file="./debutBody.html"%>

	<div class="col col-lg-8 offset-lg-2">
		<div class="row justify-content-center">
			<h2>Modifier mes informations</h2>
		</div>
		<br> <br>
		<div id="succes" style="color: green"></div>
		<div id="echec" style="color: red"></div>
		<br>
		<div class="form-row">
			<label for="nom" class="col col-lg-3">Nom</label> <input id="nom"
				type="text" class="form-control col col-lg-8" name="email"
				value="${sessionScope.user.nom}" disabled />
		</div>
		<br>
		<div class="form-row">
			<label for="prenom" class="col col-lg-3">Prenom</label> <input
				id="prenom" type="text" class="form-control col col-lg-8"
				name="prenom" value="${sessionScope.user.prenom}" disabled />
		</div>
		<br>
		<div class="form-row">
			<label for="email" class="col col-lg-3">Email</label> <input
				id="email" type="email" class="form-control col col-lg-8"
				name="email" value="${sessionScope.user.email}" disabled />
		</div>
		<br>
		<div class="form-row">
			<label for="oldpw" class="col col-lg-3">Ancien mot de passe</label> <input
				id="oldpw" type="password" class="form-control col col-lg-8"
				name="oldpw" />
		</div>
		<br>
		<div class="form-row">
			<label for="newpw" class="col col-lg-3">Nouveau mot de passe</label>
			<input id="newpw" type="password" class="form-control col col-lg-8"
				name="newpw" />
		</div>
		<br>
		<div class="form-row">
			<label for="conpw" class="col col-lg-3">Confirmez le mot de
				passe</label> <input id="conpw" type="password"
				class="form-control col col-lg-8" name="conpw" />
		</div>
		<br>
		<div class="form-row">
			<input type="button" class="btn btn-primary btn-lg btn-block"
				value="Valider les changements" onClick="modifierMDP()">
		</div>
		<br>
		<div class="form-row">
			<a href="${pageContext.request.contextPath}/login"  class="btn btn-primary btn-lg btn-block"
				 >Revenir à mon accueil</a>
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

		function modifierMDP() {
			var xhr = createXHR();
			var email = document.getElementById("email").value;
			var oldpw = document.getElementById("oldpw").value;
			var newpw = document.getElementById("newpw").value;
			var conpw = document.getElementById("conpw").value;
			data = "&oldpw=" + encodeURIComponent(oldpw) + "&newpw=" + encodeURIComponent(newpw);
			xhr.onreadystatechange = function() {

				if (xhr.readyState == 4) {
					if (xhr.status == 200)
						succes("Mot de passe modifié");
					else {
						echec(xhr.status, "Vous n'avez pas saisi le bon mot de passe");
					}
				}
			};
			if (newpw != conpw){
				echec(xhx.status, "Vous n'avez pas saisi 2 fois le même mot de passe");
			}
			xhr.open("PUT",
					"<c:out value="${pageContext.request.contextPath}"/>/rest/users/modifMDP/"
							+ email, true);
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			
			xhr.send(data);

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



	<%@include file="./finBody.html"%>
</html>