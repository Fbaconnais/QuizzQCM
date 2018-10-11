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
<body onload="listepromos()">
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
		<h1>Modification d'une promotion</h1>
		<br>

		<c:if test="${sessionScope.messageValidation != null }">
			<h2 style="color: red;">${sessionScope.messageValidation}</h2>
			<br>
		</c:if>
		<div id="succes" style="color: green"></div>
		<div id="echec" style="color: red"></div>
		<br> <br>
		<div id="results"></div>


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

		function listepromos() {
			var xhr = createXHR();

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200)
						afficherPromos(this.response);
					else
						console.log("Erreur de statut!");
				}
			};

			xhr
					.open(
							"GET",
							"<c:out value="${pageContext.request.contextPath}"/>/rest/promos/all",
							true);
			xhr.send();

		};

		function afficherPromos(xml) {
			var json = JSON.parse(xml);
			var props = [];
			var x;
			for (x = 0; x < json.length; x++) {
				props
						.push('<button onClick="afficherPromo('+json[x].id+')" class="btn btn-primary btn-mb col-xs-12 col-md-6 col-lg-3 " style="border-width: 5px;border-color:white;" name="idpromo" id="idpromo" value="'
								+ json[x].id + '" "> Modifier : ' + json[x].id
								+ ' </button>');
				props
						.push('<input type="hidden" id="'+json[x].id+'" value="'+json[x].libelle+'">');

			}
			document.getElementById("results").innerHTML = props.join("");
		}
		
		function afficherPromo(id) {
			var props = [];
			var libellepromo = document.getElementById(id.id).value;
			props
					.push('<div class="form-row"><label for="codePromo" class="col col-lg-3">Code Promo</label><input type="text" name="codePromo" id="codePromo" value="'+id.id+'" class="form-control col col-lg-8 offset-lg-1" disabled></div><br>');
			props
					.push('<div class="form-row"><label for="libPromo" class="col col-lg-3">Libelle promo</label><input type="text" name="libPromo" id="libPromo" value="'+libellepromo+'" class="form-control col col-lg-8 offset-lg-1"></div><br>')
			props
					.push('<div class="form-row"><div class="col col-lg-6"><input type="button" value="supprimer" onClick="deletePromo()" class="btn-danger btn-mb btn-block"></div>');
			props
					.push('<div class="col col-lg-6"><input type="button" value="Valider modifs" class="btn-success btn-mb btn-block" onClick="modifyPromo()"></div></div>');
			document.getElementById("results").innerHTML = props.join("");

		}

		function deletePromo() {
			var codePromo = document.getElementById("codePromo").value;
			var xhr = createXHR();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						succes("Requete exécutée");
						listepromos();
					}

					else {
						echec(xhr.status, "Echec de la modification");
					}
				}
			};
			data = "&libelle=" + encodeURIComponent(libPromo);
			xhr.open("DELETE",
					"<c:out value="${pageContext.request.contextPath}"/>/rest/promos/"
							+ codePromo, true);
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");

			xhr.send(null);
			
		}
		function modifyPromo() {
			var codePromo = document.getElementById("codePromo").value;
			var libPromo = document.getElementById("libPromo").value;
			var xhr = createXHR();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						succes("Requete exécutée");
						listepromos();
					}

					else {
						echec(xhr.status, "Echec de la modification");
					}
				}
			};
			data = "&libelle=" + encodeURIComponent(libPromo);
			xhr.open("PUT",
					"<c:out value="${pageContext.request.contextPath}"/>/rest/promos/"
							+ codePromo, true);
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


	<%@include file="../../finBody.html"%>
</html>