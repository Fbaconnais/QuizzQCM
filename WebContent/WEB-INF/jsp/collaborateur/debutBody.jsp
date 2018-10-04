<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container ">
	<div class="row">
		<div class="col col-lg-3 text-center">
			<br>
			<div class="row">
				<h1>${sessionScope.user.nom}</h1>
			</div>
			<div class="row">
				<h1>${sessionScope.user.prenom}</h1>
			</div>
			<br>
			<div class="row">
				<a href="${pageContext.request.contextPath}/collaborateur/modif"><button>Modifier vos
						informations</button></a>
			</div>
			<br>
			<div class="row">
				<a href="${pageContext.request.contextPath}/deconnection"><button>Déconnexion</button></a>
			</div>

		</div>
		