<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./entete.jsp"%>
<title>QCM - Login</title>
</head>
<body>
	<%@include file="./debutBody.html"%>

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<c:if test="${!empty musique}">
					<audio src="./audio/alarme_intrusion.mp3" autoplay="autoplay" preload="auto">
						<source src="./audio/alarme_intrusion.mp3" type="mp3">
					</audio>
				</c:if>

				<div class="well login-box">
					<form action="${pageContext.request.contextPath}/login"
						method="post">
						<h2>Authentification</h2>
						<br> <br>

						<c:if test="${sessionScope.profilCon == \"erreur\"}">
							<div class="row justify-content-lg-center">
								<h2 style="color: red">Erreur d'authentification, réessayez</h2>
							</div>
							<br>
							<br>
						</c:if>


						<div class="row justify-content-lg-center">
							<div class="form-group col-lg-8 ">
								<label for="username-email">E-mail</label> <input
									id="username-email" placeholder="E-mail" type="email"
									class="form-control" name="email" />
							</div>
						</div>
						<div class="row justify-content-lg-center">
							<div class="form-group col-lg-8 ">
								<label for="password">Mot de passe</label> <input id="password"
									placeholder="Mot de passe" type="password" class="form-control"
									name="password" />
							</div>
						</div>

						<div class="row justify-content-lg-center">
							<input type="submit" class="btn btn-success btn-login-submit"
								value="Valider" />
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>


	<%@include file="./finBody.html"%>
</html>