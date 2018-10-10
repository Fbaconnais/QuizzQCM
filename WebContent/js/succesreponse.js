function succes(reponse) {
	document.getElementById("succes").innerHTML = reponse;
	document.getElementById("echec").innerHTML = "";
}
function echec(codeReponse, reponse) {
	document.getElementById("echec").innerHTML = reponse;
	document.getElementById("succes").innerHTML = "";
}