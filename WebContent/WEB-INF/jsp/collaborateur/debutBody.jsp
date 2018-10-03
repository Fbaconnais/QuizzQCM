<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container text-center">
		<div class="row justify-content-md-center">
			<div class="col col-lg-11 text-center">
			<div class="col col-lg-"">
			<div class="row">
				<h1>${sessionScope.user.nom}</h1>
			</div>
			<div class="row">
				<h1>${sessionScope.user.prenom}</h1>
			</div>
			<div class="row">
				<a href="collaborateur/modif"><button>Modifier vos informations</button></a>
			</div>
			<div class="row">
				<a href="deconnection"><button>Déconnection</button></a>
			</div>

		</div>
		<div class="col col-lg-9">