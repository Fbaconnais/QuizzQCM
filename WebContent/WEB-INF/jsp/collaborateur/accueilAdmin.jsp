<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./entete.jsp"%>
<title>QCM - Accueil admin</title>
</head>
<body>

	<%@include file="./debutBody.jsp"%>
	<div class="col col-lg-7 justify-content-lg-center offset-lg-1">
		<br> <input type="button" onClick="gestionPerso()"
			class="btn btn-primary btn-lg btn-block" value="Inscriptions">
		<br> <input type="button" onClick="consulterLogs()"
			class="btn btn-primary btn-lg btn-block"
			value="Consultation des logs incidents">
	</div>
	</div>
	<br>
	<div class="row justify-content-lg-center">
		<div class="col col-lg-10 offset-lg-1">
			<div id="succes" style="color: green"></div>
			<div id="echec" style="color: red"></div>
		</div>
		<br> <br>
	</div>
	<div class="row justify-content-lg-center">
		<div class="col col-lg-10 offset-lg-1" id="results"></div>
	</div>
	<br>
	<div class="row justify-content-lg-center">
		<div class="col col-lg-10 offset-lg-1"><a href="${pageContext.request.contextPath}/collaborateur"
			class="btn btn-primary btn-lg btn-block"> Revenir à l'accueil admin </a></div>
	</div>

	<script>
		function createXHR() {
			if (window.XMLHttpRequest) {
				xhr = new XMLHttpRequest();
			} else if (window.ActiveXObject) //  Internet Explorer
			{
				xhr = new ActiveXObject("Msxml2.XMLHTTP");
			}

			return xhr;

		}
		function gestionPerso() {
			document.getElementById("succes").innerHTML = "";
			document.getElementById("echec").innerHTML = "";
			var props = [];
			props.push('<div class="col col-lg-8 offset-lg-2">')
			props
					.push('<input type="button" onClick="newPerso()" class="form-row btn btn-primary btn-mb btn-block" value="Ajouter un nouveau responsable"><br>');
			props
					.push('<input type="button" onClick="modifPerso()" class="form-row btn btn-primary btn-mb btn-block" value="Modifier/Supprimer un responsable"><br>');
			props.push('</div>')
			document.getElementById("results").innerHTML = props.join("");

		}

		function newPerso() {
			var props = [];
			props
					.push('<div class="form-row"><label for="name" class="col col-lg-3"> Nom </label>');
			props
					.push('<input type="text" class="col col-lg-8 offset-lg-1" name="nom" id="nom" placeholder="nom" required ></div><br>');
			props
					.push('<div class="form-row"><label for="prenom" class="col col-lg-3"> Prénom </label>');
			props
					.push('<input type="text" class="col col-lg-8 offset-lg-1" name="prenom" id="prenom" placeholder="prénom" required ></div><br>');
			props
					.push('<div class="form-row"><label for="email" class="col col-lg-3"> Email </label>');
			props
					.push('<input type="email" class="col col-lg-8 offset-lg-1" name="email" id="email" placeholder="email" required ></div><br>');
			props
					.push('<div class="form-row"><label for="role" class="col col-lg-3"> Role </label>');
			props
					.push('<select class="col col-lg-8 offset-lg-1" name="role" id="role">');
			props.push('<option value="3" selected>Formateur</option>');
			props.push('<option value="4" >Cellule de recrutement</option>');
			props.push('<option value="5" >Responsable de formation</option>');
			props
					.push('<option value="6" >Administrateur</option></select></div><br>');
			props
					.push('<div class="form-row"><div class="col col-lg-6"><input type="button" value="annuler" class="btn btn-primary btn-mb btn-block" onClick="gestionPerso()"></div>');
			props
					.push('<div class="col col-lg-6"><input type="button" value="Valider" onClick="addBDD()" class="btn btn-primary btn-mb btn-block"></div></div>');
			document.getElementById("results").innerHTML = props.join("");

		}

		function addBDD() {
			var xhr = createXHR();
			var nom = document.getElementById("nom").value;
			var prenom = document.getElementById("prenom").value;
			var email = document.getElementById("email").value;
			var profil = document.getElementById("role").value;
			var expressionReguliere = /^(([^<>()[]\.,;:s@]+(.[^<>()[]\.,;:s@]+)*)|(.+))@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}])|(([a-zA-Z-0-9]+.)+[a-zA-Z]{2,}))$/;
			var message = "";
			if (nom == null || nom === undefined) {
				message += "Saisissez un nom - ";
			}
			if (prenom == null || prenom === undefined) {
				message += "Saisissez un prenom - ";
			}
			if (email == null || email === undefined) {
				message += "Saisissez un email - ";
			} else if (!(expressionReguliere.test(email))) {
				message += "L'adresse email n'est pas valide";
			}
			if (message != "") {
				echec(1, message);
			} else {
				data = "&nom=" + encodeURIComponent(nom) + "&prenom="
						+ encodeURIComponent(prenom) + "&email="
						+ encodeURIComponent(email) + "&profil="
						+ encodeURIComponent(profil);

				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200)
							succes("Collaborateur ajouté");
						else
							echec(xhr.status, xhr.responseText);
					}
				};
				xhr
						.open(
								"PUT",
								"<c:out value="${pageContext.request.contextPath}"/>/rest/users/new/",
								true);
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-type",
						"application/x-www-form-urlencoded");
				xhr.send(data);

			}
		}

		function modifPerso() {
			var xhr = createXHR();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200)
						afficherListe(this.response);
					else
						echec(xhr.status, xhr.responseText);
				}
			};
			xhr
					.open(
							"GET",
							"<c:out value="${pageContext.request.contextPath}"/>/rest/users/collaborateurs/",
							true);
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhr.send();
		}
		function afficherListe(xml) {
			var json = JSON.parse(xml);
			var props = [];
			var x;
			for (x = 0; x < json.length; x++) {
				props
						.push('<div class="col"><input type="button" value="Modifier : '
								+ json[x].prenom
								+ ' '
								+ json[x].nom
								+ ' - '
								+ json[x].email
								+ '" class="btn-primary btn-mb btn-block" onClick="afficherColl('
								+ json[x].idUtilisateur + ')"></div>');
				props
						.push('<input type="hidden" id="prenom'+json[x].idUtilisateur+'" value="'+json[x].prenom+'">');
				props
						.push('<input type="hidden" id="nom'+json[x].idUtilisateur+'" value="'+json[x].nom+'">');
				props
						.push('<input type="hidden" id="email'+json[x].idUtilisateur+'" value="'+json[x].email+'">');
				props
						.push('<input type="hidden" id="pro'+json[x].idUtilisateur+'" value="'+json[x].profil.id+'">');
			}
			document.getElementById("results").innerHTML = props.join("");

		}

		function afficherColl(id) {
			var prenom = document.getElementById('prenom' + id).value;
			var nom = document.getElementById('nom' + id).value;
			var email = document.getElementById('email' + id).value;
			var profil = document.getElementById('pro' + id).value;
			var props = [];
			props
					.push('<div class="form-row"><label for="nom" class="col col-lg-3"> Nom </label>');
			props
					.push('<input type="text" class="col col-lg-8 offset-lg-1" name="nom" id="nom" value="'+nom+'" required ></div><br>');
			props
					.push('<div class="form-row"><label for="prenom" class="col col-lg-3"> Prénom </label>');
			props
					.push('<input type="text" class="col col-lg-8 offset-lg-1" name="prenom" id="prenom" value="'+prenom+'" required ></div><br>');
			props
					.push('<div class="form-row"><label for="email" class="col col-lg-3"> Email </label>');
			props
					.push('<input type="email" class="col col-lg-8 offset-lg-1" name="email" id="email" value="'+email+'" required ></div><br>');
			props
					.push('<div class="form-row"><label for="role" class="col col-lg-3"> Role </label>');
			props
					.push('<select class="col col-lg-8 offset-lg-1" name="role" id="role">');

			props.push('<option value="3"');
			if (profil == 3) {
				props.push(' selected ');
			}
			props.push('>Formateur</option>');
			props.push('<option value="4"');
			if (profil == 4) {
				props.push(' selected ');
			}
			props.push('>Cellule de recrutement</option>');
			props.push('<option value="5"');
			if (profil == 5) {
				props.push(' selected ');
			}
			props.push('>Responsable de formation</option>');
			props.push('<option value="6"');
			if (profil == 6) {
				props.push(' selected ');
			}
			props.push('>Administrateur</option></select></div><br>');
			props.push('<div class="form-row"><div class="col col-lg-6"><input type="button" value="Supprimer" class="btn btn-primary btn-mb btn-block" onClick="deleteBDD('+id+')"></div>');
	props.push('<div class="col col-lg-6"><input type="button" value="Modifier" onClick="updateBDD('+id+')" class="btn btn-primary btn-mb btn-block"></div></div>');
			
			
			
			document.getElementById("results").innerHTML = props.join("");
		}
		
		function deleteBDD(id){
			var xhr = createXHR();

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						succes("Requete exécutée");
					}

					else {
						echec(xhr.status,
								"Echec de la suppresion");
					}
				}
			};

			xhr.open("DELETE",
					"<c:out value="${pageContext.request.contextPath}"/>/rest/users/"+ id, true);
			xhr.send(null);
		}
		
		function updateBDD(id){
			var xhr = createXHR();
			var nom = document.getElementById("nom").value;
			var prenom = document.getElementById("prenom").value;
			var email = document.getElementById("email").value;
			var profil = document.getElementById("role").value;
			var expressionReguliere = /^(([^<>()[]\.,;:s@]+(.[^<>()[]\.,;:s@]+)*)|(.+))@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}])|(([a-zA-Z-0-9]+.)+[a-zA-Z]{2,}))$/;
			var message = "";
			if (nom == null || nom === undefined) {
				message += "Saisissez un nom - ";
			}
			if (prenom == null || prenom === undefined) {
				message += "Saisissez un prenom - ";
			}
			if (email == null || email === undefined) {
				message += "Saisissez un email - ";
			} else if (!(expressionReguliere.test(email))) {
				message += "L'adresse email n'est pas valide";
			}
			if (message != "") {
				echec(1, message);
			} else {
				data = "&nom=" + encodeURIComponent(nom) + "&prenom="
						+ encodeURIComponent(prenom) + "&email="
						+ encodeURIComponent(email) + "&profil="
						+ encodeURIComponent(profil);

				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200)
							succes("Collaborateur modifié");
						else
							echec(xhr.status, xhr.responseText);
					}
				};
				xhr
						.open(
								"PUT",
								"<c:out value="${pageContext.request.contextPath}"/>/rest/users/" + id,
								true);
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-type",
						"application/x-www-form-urlencoded");
				xhr.send(data);
		}}

		function consulterLogs() {
			document.getElementById("results").innerHTML = "<h1>en construction</h1>";
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