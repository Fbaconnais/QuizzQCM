<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../entete.html"%>
<title>QCM - Choix de l'épreuve</title>
</head>
<body>
<%@include file="../debutBody.html"%>
	<div class="container text-center">
		<div class="row justify-content-md-center">
			<div class="col col-lg-8 text-center">
				<div class="row justify-content-lg-center">
					<h1>Epreuves</h1>
				</div>
				<hr>
				<c:choose>
					<c:when test="${empty sessionScope.listeEpreuves }">
						<div class="row">Aucune épreuve n'est programmée pour vous</div>
					</c:when>
					<c:otherwise>
						<ul class="list-group col-12">
							<c:forEach var="panier" items="${sessionScope.listeEpreuves}">

								<li
									class="list-group-item d-flex justify-content-between align-items-center">
									<div class="col col-lg-6 justify-content-lg-center">
										<h3>${epreuve.nom }</h3>
									</div>
									<div class="col col-lg-2">
										<a href="action?action=open&id=${epreuve.id}"><img
											src="/images/epreuve et test/exam.png" title="Démarrer l'epreuve ${panier.nom }"></a>
									</div>
								</li>

								<br>
							</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
				

			</div>
		</div>
	</div>
<%@include file="../finBody.html"%>
</body>
</html>