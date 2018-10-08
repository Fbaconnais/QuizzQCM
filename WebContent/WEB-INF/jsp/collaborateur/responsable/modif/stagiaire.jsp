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
		<h1>Modification d'un candidat</h1>
		<br>
		<c:if test="${sessionScope.messageValidation != null }">
			<h2 style="color: red;">${sessionScope.messageValidation}</h2>
			<br>
		</c:if>
		<div id="succes" style="color: green"></div>
		<div id="echec" style="color: red"></div>
		<br>

		<div class="col col-lg-10 justify-content-lg-center offset-lg-1">
			<div id="afficherinfos">
				<div class="form-row">
					<label for="nom" class="col col-lg-4">Recherche par
						nom/mail </label> <input type="text"
						class="form-control col col-lg-7 offset-lg-1" name="nom" id="nom">
				</div>
				<br>
				<div id="results"></div>
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
								echec(xhr.status, xhr.responseText);
						}
					};
					var input = document.getElementById('nom');
					var nommail = input.value;
					xhr.open("GET",
							"<c:out value="${pageContext.request.contextPath}"/>/rest/users/"
									+ nommail + "/all", true);
					xhr.setRequestHeader("Accept", "application/json");
					xhr.send();

				});

		function afficherCandidats(xml) {
			var json = JSON.parse(xml);
			var props = [];
			var x;
			for (x = 0; x < json.length; x++) {
				props
						.push('<button onClick="afficher('
								+ json[x].idUtilisateur
								+ ')" class="form-row btn btn-primary btn-mb btn-block" name="idutil" id="idutil" value="'
								+ json[x].idUtilisateur + '" ">Modifier : '
								+ json[x].nom + '  ' + json[x].prenom + ' - '
								+ json[x].email + ' </button><br>');
			}
			document.getElementById("results").innerHTML = props.join("");
		}

		function afficher(id) {
			var xhr = createXHR();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200)
						afficherCandidat(this.response);
					else
						echec(xhr.status, xhr.responseText);
				}
			};
			xhr.open("GET",
					"<c:out value="${pageContext.request.contextPath}"/>/rest/users/"
							+ id, true);
			xhr.setRequestHeader("Accept", "application/json");
			xhr.send();
		}

		function afficherCandidat(xml) {
			var json = JSON.parse(xml);

			var props = [];
			props.push('<form>');
			props
					.push('<input type="hidden" name="idutil" value="'+json.utilisateur.idUtilisateur+'" id="idutil">');
			props
					.push('<div class="form-row"><label for="nom" class="col col-lg-3">Nom </label>');
			props
					.push('<input type="text" class="form-control col col-lg-8 offset-lg-1" name="nom" id="nom" required value="'+json.utilisateur.nom+'"></div><br>');
			props
					.push('<div class="form-row"><label for="prenom" class="col col-lg-3">Prenom </label>');
			props
					.push('<input type="text" class="form-control col col-lg-8 offset-lg-1" name="prenom" id="prenom" required value="'+json.utilisateur.prenom+'"></div><br>');
			props
					.push('<div class="form-row"><label for="email" class="col col-lg-3">Email </label>');
			props
					.push('<input type="email" class="form-control col col-lg-8 offset-lg-1" name="email" id="email" required value="'+json.utilisateur.email+'"></div><br>');
			props
					.push('<div class="form row"><label for="profil" class="col col-lg-3">Profil</label><select name="profil" id="profil" onchange="afficherpromos()" class="form-control col col-lg-8 offset-lg-1">');
			if (json.utilisateur.profil.id == '1') {
				props.push('<option value="1" selected>Stagiaire</option>');
				props.push('<option value="2">Candidat libre</option>');
				props.push('</select></div><br>');
				props
						.push('<div class="form-row"><label for="promo" class="col col-lg-3" id="labelselect">Promotion</label>');
				props
						.push('<select class="form-control col col-lg-8 offset-lg-1" name="promo" id="promo">');

				var x;
				for (x = 0; x < json.promotions.length; x++) {
					props.push('<option ');
					if (json.utilisateur.promotion.id == json.promotions[x].id) {
						props.push('selected');
					}
					props.push('>' + json.promotions[x].id + '</option>');
				}
				props.push('</select></div><br>');
			} else {
				props.push('<option value="1" >Stagiaire</option>');
				props
						.push('<option value="2" selected>Candidat libre</option>');
				props.push('</select></div><br>');
				props
						.push('<div class="form-row"><label for="promo" class="col col-lg-3" id="labelselect" style="display:none;">Promotion</label>');
				props
						.push('<select class="form-control col col-lg-8 offset-lg-1" name="promo" id="promo" style="display:none;">');
				var x;
				for (x = 0; x < json.promotions.length; x++) {
					props
							.push('<option>' + json.promotions[x].id
									+ '</option>');
				}

				props.push('</select></div><br>');
			}
			props
					.push('<div class="form-row"><div class="col col-lg-6"><input type="button" value="supprimer" onClick="deleteUser('
							+ json.utilisateur.idUtilisateur
							+ ')" class="btn-primary btn-mb btn-block"></div>');
			props
					.push('<div class="col col-lg-6"><input type="button" value="Valider modifs" class="btn-primary btn-mb btn-block" onClick="modifyUser()"></div></div></form>');

			document.getElementById("afficherinfos").innerHTML = props.join("");
		}
		function afficherpromos() {
			var type = document.getElementById('profil');
			var promos = document.getElementById('promo');
			var label = document.getElementById('labelselect')
			if (type.value == 1) {
				promos.style.display = 'inline';
				label.style.display = 'inline';
			} else {
				promos.style.display = 'none';
				label.style.display = 'none';
			}
		}

		function deleteUser(id) {
			var xhr = createXHR();

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						succes("Requete exécutée");
					}

					else {
						echec(xhr.status,
								"Echec de la suppresion - vérifier l'inscription à des tests");
					}
				}
			};

			xhr.open("DELETE",
					"<c:out value="${pageContext.request.contextPath}"/>/rest/users/"
							+ id, true);
			xhr.send(null);
		}

		function modifyUser() {
			var xhr = createXHR();
			var profil = document.getElementById("profil").value;
			var id = document.getElementById("idutil").value;
			var nom = document.getElementById("nom").value;
			var prenom = document.getElementById("prenom").value;
			var email = document.getElementById("email").value;
			var profil = document.getElementById("profil").value;
			var promo = document.getElementById("promo").value;

			if (profil == '1') {
				data = "&nom=" + encodeURIComponent(nom) + "&prenom="
						+ encodeURIComponent(prenom) + "&email="
						+ encodeURIComponent(email) + "&profil="
						+ encodeURIComponent(profil) + "&promo="
						+ encodeURIComponent(promo);
			} else {
				data = "&nom=" + encodeURIComponent(nom) + "&prenom="
						+ encodeURIComponent(prenom) + "&email="
						+ encodeURIComponent(email) + "&profil="
						+ encodeURIComponent(profil);
			}
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						succes("Requete exécutée");
					}

					else {
						echec(xhr.status, "Echec de la modification");
					}
				}
			};
			xhr.open("PUT",
					"<c:out value="${pageContext.request.contextPath}"/>/rest/users/"
							+ id, true);
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