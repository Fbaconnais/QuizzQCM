function afficherTestsPromo(id) {

}

function afficherTestsUtil(id) {
	var xhr = createXHR();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200)
				afficherListeTestUtil(this.response);
			else
				echec(xhr.status, xhr.responseText);
		}
	};
	xhr.open("GET", path + "/rest/epreuve/candidat/" + id, true);
	xhr.setRequestHeader("Accept", "application/json");
	xhr.send();
}

function afficherListeTestUtil(xml) {
	var json = JSON.parse(xml);
	console.log(json);
	var props = [];
	var props2 = [];

	props2.push('<div class="row justify-content-center"><h3>'
			+ json.epreuves[0].candidat.prenom + ' '
			+ json.epreuves[0].candidat.nom + '</h3></div>');

	var x;
	for (x = 0; x < json.epreuves.length; x++) {

		if (x % 4 == 0) {
			props.push('<div class="row"');
		}
		props
				.push('<div class="col-lg-3 col-md-4 col-xs-6"><div class="card" style="width: 25%;"><img class="card-img-top"	src="'
						+ path
						+ json.epreuves[x].test.logoLangage
						+ '" alt="Logo du langage de programmation"><div class="card-body"><h5 class="card-title">'
						+json.epreuves[x].niveauCandidat+ '</h5><p class="card-text"> Note : '
						+ json.epreuves[x].noteCandidat
						+ ' /20</p></div></div></div>');
		if (x % 4 == 3) {
			props.push('</div>');
		}
	}
	document.getElementById("tests").innerHTML = props.join("");
	document.getElementById("afficherinfos").innerHTML = props2.join("");
}